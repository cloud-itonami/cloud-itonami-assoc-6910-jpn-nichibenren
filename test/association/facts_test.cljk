(ns association.facts-test
  "The catalog as a body of citations, not as a count.

  Nine entries were added on 2026-09-09, which is the size at which a catalog
  starts to acquire a fabricated URL, a copied-down date, or a second entry for
  a document already in the table. The assertions below are the ones that would
  have caught each of those, so they are written over the whole catalog rather
  than over the entry someone happened to look at."
  (:require [kotoba.lang.text :as str]
            [clojure.edn :as edn]
            [clojure.test :refer [deftest is testing]]
            [association.facts :as facts]))

(def ^:private slug "nichibenren")
(def ^:private entries (vec (facts/spec-basis slug)))

(def ^:private tx-data
  "The file the Kotoba port is generated from. Reading it here is what makes
   the two files one catalog instead of two."
  (edn/read-string (slurp "data/datascript-tx.edn")))

(deftest nichibenren-has-spec-basis
  (is (seq entries))
  (is (every? #(str/starts-with? (:association-rule/url %) "https://www.nichibenren.or.jp/") entries))
  (is (every? #(= "6910" (:association-rule/isic %)) entries))
  (is (every? #(= "JPN" (:association-rule/country %)) entries))
  (is (every? #(= slug (:association-rule/association %)) entries)))

(deftest every-citation-is-distinct
  ;; A catalog grows by appending, and appending is how the same document gets
  ;; cited twice under two ids.
  (let [ids (map :association-rule/id entries)
        urls (map :association-rule/url entries)]
    (is (= (count entries) (count (distinct ids))) (pr-str (frequencies ids)))
    (is (= (count entries) (count (distinct urls))) (pr-str (frequencies urls)))
    (is (every? #(str/starts-with? % (str slug ".")) ids))))

(deftest kinds-and-provenance-come-from-a-closed-vocabulary
  ;; A typo'd keyword is not an error anywhere else -- it just silently answers
  ;; no query.
  (is (every? #{:self-regulatory-code :governance-program :practice-guidance}
              (map :association-rule/kind entries)))
  (is (every? #{:official-association-site}
              (map :association-rule/url-provenance entries))))

(deftest dates-are-iso-and-ordered
  (doseq [e entries]
    (testing (:association-rule/id e)
      (let [est (:association-rule/established-date e)
            rev (:association-rule/last-revised-date e)]
        (is (re-matches #"\d{4}-\d{2}-\d{2}" est))
        ;; Absent is a legitimate answer -- the profile page and the 2016
        ;; Mission Statement print no amendment. A guess is not.
        (when rev
          (is (re-matches #"\d{4}-\d{2}-\d{2}" rev))
          (is (not (neg? (compare rev est)))
              "a document cannot have been amended before it was enacted"))
        (is (re-matches #"\d{4}-\d{2}-\d{2}" (:association-rule/retrieved-at e)))))))

(deftest the-cljc-and-the-data-file-are-one-catalog
  ;; src/association_facts.kotoba is generated from data/datascript-tx.edn while
  ;; this map is written out by hand, so without this the two could disagree and
  ;; the .kotoba parity test would report the mismatch against the wrong file.
  (is (= (count tx-data) (count entries)))
  (doseq [[i [from-data from-cljc]] (map-indexed vector (map vector tx-data entries))]
    (testing (str "entry " i)
      (is (= (dissoc from-data :association-rule/topic)
             (dissoc from-cljc :association-rule/topic)))
      (is (= (set (:association-rule/topic from-data))
             (:association-rule/topic from-cljc))
          "the data file writes topics as a vector, the map holds a set"))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "keidanren")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage [slug "keidanren"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["keidanren"] (:missing-associations c)))
    (is (str/includes? (:note c) (str (count entries) " nichibenren entries")))))

(deftest by-topic-filters
  ;; Named entries, not counts: a count passes when by-topic returns the wrong
  ;; entries in the right number.
  (is (= ["nichibenren.basic-rules-on-duties-of-practicing-attorneys"
          "nichibenren.basic-rules-on-duties-of-foreign-special-members"
          "nichibenren.basic-rules-on-duties-of-registered-foreign-lawyers"]
         (mapv :association-rule/id (facts/by-topic slug :ethics))))
  (is (= ["nichibenren.rules-on-verification-of-client-identity"
          "nichibenren.regulations-on-verification-of-client-identity"]
         (mapv :association-rule/id (facts/by-topic slug :anti-money-laundering))))
  (is (= ["nichibenren.rules-on-disclosure-of-disciplinary-records"]
         (mapv :association-rule/id (facts/by-topic slug :discipline))))
  (is (= 5 (count (facts/by-topic slug :governance))))
  (is (= 7 (count (facts/by-topic slug :member-conduct))))
  (is (empty? (facts/by-topic slug :labor)))
  (is (empty? (facts/by-topic "keidanren" :ethics))))
