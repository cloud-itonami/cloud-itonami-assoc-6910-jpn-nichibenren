(ns association.facts
  "Industry self-regulatory rule catalog for the Japan Federation of Bar
  Associations (日本弁護士連合会 / JFBA, Nichibenren) -- an 11th industry-
  association-level source (see cloud-itonami-assoc-6419-jpn-zenginkyo,
  -6512-jpn-sonpo, -6612-jpn-jsda, -6419-deu-bankenverband, -6612-usa-finra,
  -6512-usa-naic, -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf,
  -6511-jpn-seiho for the first ten) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The FIRST entry aligned to
  ISIC 6910 (legal activities) -- a new industry code for this family,
  distinct from 6920 (accounting/tax/audit, already covered by
  jicpa/aicpa). Every entry cites an OFFICIAL nichibenren.or.jp URL --
  never fabricated. A rule not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.

  Basic Rules on the Duties of Practicing Attorneys was verified by
  directly reading the source PDF cover page text via the Read tool
  (same strictest-tier verification as sonpo/bankenverband/naic/jicpa/
  aicpa/seiho) -- the enactment (Federation's Rule No. 70 of November
  10, 2004) and most recent amendment (June 11, 2021) dates are printed
  on the document's own cover page, along with the official English
  translation date (June 2022). The organization-profile page was
  directly WebFetch-verified.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"nichibenren"
   [{:association-rule/id "nichibenren.basic-rules-on-duties-of-practicing-attorneys"
     :association-rule/title "Basic Rules on the Duties of Practicing Attorneys (弁護士職務基本規程, Federation's Rule No. 70)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Basic_Rules_Practicing.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2004-11-10"
     :association-rule/last-revised-date "2021-06-11"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:ethics :member-conduct}}
    {:association-rule/id "nichibenren.about-jfba-profile"
     :association-rule/title "What is the JFBA? (organization profile)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.nichibenren.or.jp/en/about/us/profile.html"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1949-09-01"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:governance}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-6910-jpn-nichibenren Wave 0 (ADR-2607141700): "
                 (count (get catalog "nichibenren")) " nichibenren entries seeded "
                 "with an official nichibenren.or.jp citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
