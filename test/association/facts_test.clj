(ns association.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest nichibenren-has-spec-basis
  (let [sb (facts/spec-basis "nichibenren")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:association-rule/url %) "https://www.nichibenren.or.jp/") sb))
    (is (every? #(= "6910" (:association-rule/isic %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "keidanren")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["nichibenren" "keidanren"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["keidanren"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["nichibenren.basic-rules-on-duties-of-practicing-attorneys"]
         (mapv :association-rule/id (facts/by-topic "nichibenren" :ethics))))
  (is (empty? (facts/by-topic "nichibenren" :labor)))
  (is (empty? (facts/by-topic "keidanren" :ethics))))
