;; scripts/verify_citations.cljs — fetch every URL in the catalog and report
;; what the server actually answered.
;;
;; The repo's whole claim is that each entry cites an official
;; www.nichibenren.or.jp document that exists. Nothing in the test suite can
;; check that -- the suite is hermetic on purpose -- so the claim is only ever
;; as good as the last time someone looked. This is how you look.
;;
;;   nbb scripts/verify_citations.cljs
;;
;; Exit 0 every URL answered 2xx with the content type its extension implies.
;;      1 at least one did not (the line says which, and what came back).
;;      2 REFUSED -- the catalog could not be read, so nothing was checked.
;;        Not 0: "could not check" must not look like "checked, all fine".
;;
;; Link rot is the expected failure here and it is not automatically a bug in
;; this repo: JFBA moves documents. A non-2xx means go to
;; https://www.nichibenren.or.jp/en/about/us/regulations.html and find where the
;; document went -- it does not mean delete the entry, and it never means
;; substitute a URL that was not read off a JFBA page.

(ns verify-citations
  (:require ["fs" :as fs]
            [kotoba.lang.text :as str]
            [cljs.reader :as reader]))

(def ^:private tx-path "data/datascript-tx.edn")

(defn- refuse! [msg]
  (println "REFUSED:" msg)
  (println "nothing was checked; this is not a pass")
  (js/process.exit 2))

(def ^:private entries
  (let [tx (try (reader/read-string (fs/readFileSync tx-path "utf8"))
                (catch :default e (refuse! (str tx-path " did not read: " (.-message e)))))]
    (when-not (and (vector? tx) (seq tx))
      (refuse! (str tx-path " read as " (type tx) " with no entries")))
    tx))

(defn- expected-type [url]
  (if (str/ends-with? url ".pdf") "application/pdf" "text/html"))

(defn- head [url]
  (-> (js/fetch url #js {:method "GET" :redirect "follow"
                         :headers #js {"User-Agent" "cloud-itonami-assoc-6910-jpn-nichibenren citation check"}})
      (.then (fn [r] {:status (.-status r)
                      :type (or (.get (.-headers r) "content-type") "")}))
      (.catch (fn [e] {:status 0 :type "" :error (.-message e)}))))

(defn- check [entry]
  (let [url (:association-rule/url entry)]
    (.then (head url)
           (fn [{:keys [status type error]}]
             (let [want (expected-type url)
                   ok? (and (<= 200 status 299) (str/includes? type want))]
               {:id (:association-rule/id entry)
                :url url :status status :type type :error error :want want :ok? ok?})))))

(-> (js/Promise.all (clj->js (map check entries)))
    (.then
     (fn [results]
       (let [results (js->clj results :keywordize-keys true)
             bad (remove :ok? results)]
         (doseq [{:keys [ok? status type url id error want]} results]
           (println (str (if ok? "  ok  " "FAIL  ") status
                         " [" (first (str/split (or type "") #";")) "] "
                         url "  (" id ")"
                         (when-not ok?
                           (str "  -- wanted 2xx " want (when error (str " / " error)))))))
         ;; The floor: a run that checked nothing must not print a clean line.
         (println (str "CHECKED\t" (count results) "\tFAILED\t" (count bad)))
         (when (zero? (count results)) (refuse! "no entries were checked"))
         (js/process.exit (if (seq bad) 1 0)))))
    (.catch (fn [e] (refuse! (str "the run itself failed: " (.-message e))))))
