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
  stop; extend the catalog, do not invent an id/url.

  `data/datascript-tx.edn` holds the same eleven entries and is the file
  `scripts/gen_association_facts_kotoba.cljs` generates the Kotoba port
  from; `association.facts-test` asserts this map and that file agree
  field for field, so the three readings cannot drift apart silently.

  Provenance. The whole catalog is verified at the strictest tier the
  family uses (as for sonpo/bankenverband/naic/jicpa/aicpa/seiho): every
  PDF was fetched from www.nichibenren.or.jp and its enactment and most
  recent amendment dates read off the document's own cover page, which
  is where JFBA prints them. Entries 1-2 were verified 2026-07-15
  (Basic Rules on the Duties of Practicing Attorneys from the PDF cover
  page, the organization profile by WebFetch). Entries 3-11 were added
  2026-09-09 from the two pages that enumerate JFBA's English-language
  rules -- `/en/about/us/regulations.html` (JFBA Rules and Regulations)
  and `/en/about/us/policies.html` -- each URL re-fetched and answering
  200 with `application/pdf`.

  The dates are the documents' own, not a summary of them: the Articles
  of Association were adopted 1949-07-09 and last amended 2021-06-11;
  Rules of the Federation No. 95 on client identity verification was
  adopted 2012-12-07 and last amended 2023-03-03, while its implementing
  Regulation No. 154 was adopted 2012-12-20 and last amended 2024-11-20.
  Where a document's cover page prints no amendment (the profile page and
  the 2016 Mission Statement), `:association-rule/last-revised-date` is
  absent rather than guessed.

  JFBA labels these English texts unofficial and tentative translations;
  the citation, dates and rule numbers are theirs, the translation is a
  convenience, and the Japanese text governs.")

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
     :association-rule/topic #{:governance}}
    {:association-rule/id "nichibenren.articles-of-association"
     :association-rule/title "Articles of Association of Japan Federation of Bar Associations (日本弁護士連合会会則)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Articl_Bar_Associations.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1949-07-09"
     :association-rule/last-revised-date "2021-06-11"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:governance}}
    {:association-rule/id "nichibenren.basic-rules-on-duties-of-foreign-special-members"
     :association-rule/title "Basic Rules on the Duties of Foreign Special Members (外国特別会員基本規程, Federation's Rule No. 25)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Basic_Rules_Foreign.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1987-01-24"
     :association-rule/last-revised-date "2021-06-11"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:ethics :member-conduct}}
    {:association-rule/id "nichibenren.basic-rules-on-duties-of-registered-foreign-lawyers"
     :association-rule/title "Basic Rules on the Duties of Registered Foreign Lawyers / Gaikokuho-Jimu-Bengoshi (外国法事務弁護士等職務基本規程, Federation's Rule No. 100)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Basic_Rules_Registered.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2014-12-05"
     :association-rule/last-revised-date "2021-06-11"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:ethics :member-conduct}}
    {:association-rule/id "nichibenren.basic-rules-for-joint-corporation-members"
     :association-rule/title "Basic Rules for Joint Corporation Members (共同法人会員基本規程, Federation's Rule No. 105)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Joint_Corp_Rules.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2021-06-11"
     :association-rule/last-revised-date "2021-12-03"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:governance :member-conduct}}
    {:association-rule/id "nichibenren.rules-on-disclosure-of-disciplinary-records"
     :association-rule/title "Rules Concerning Disclosure of Disciplinary Records of Registered Foreign Lawyers (外国法事務弁護士等の懲戒処分歴の開示に関する規程, Federation's Rule No. 88)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Rules_Disclosure.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2008-12-05"
     :association-rule/last-revised-date "2021-06-11"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:governance :discipline}}
    {:association-rule/id "nichibenren.rules-on-office-name-of-registered-foreign-lawyers"
     :association-rule/title "Rules on Office Name of Registered Foreign Lawyers (外国法事務弁護士事務所等の名称等に関する規程, Federation's Rule No. 76)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :practice-guidance
     :association-rule/url "https://www.nichibenren.or.jp/library/en/about/data/Rules_Office_Name.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2006-03-03"
     :association-rule/last-revised-date "2021-06-11"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:member-conduct}}
    {:association-rule/id "nichibenren.rules-on-verification-of-client-identity"
     :association-rule/title "Rules Concerning Verification of Client Identity and Retention of Records (依頼者の本人特定事項等の確認及び記録保存等に関する規程, Rules of the Federation No. 95)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.nichibenren.or.jp/library/pdf/jfba_info/rules/kaiki/kaiki_no_95_en_2404.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2012-12-07"
     :association-rule/last-revised-date "2023-03-03"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:anti-money-laundering :member-conduct}}
    {:association-rule/id "nichibenren.regulations-on-verification-of-client-identity"
     :association-rule/title "Regulations Concerning Verification of Client Identity and Retention of Records (依頼者の本人特定事項等の確認及び記録保存等に関する規則, Regulation No. 154)"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :practice-guidance
     :association-rule/url "https://www.nichibenren.or.jp/library/pdf/jfba_info/rules/kisoku/kisoku_no_154_en_2404.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2012-12-20"
     :association-rule/last-revised-date "2024-11-20"
     :association-rule/retrieved-at "2026-09-09"
     :association-rule/topic #{:anti-money-laundering :member-conduct}}
    {:association-rule/id "nichibenren.mission-statement-on-international-affairs"
     :association-rule/title "Mission Statement on International Affairs"
     :association-rule/association "nichibenren"
     :association-rule/isic "6910"
     :association-rule/country "JPN"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.nichibenren.or.jp/library/en/document/data/mission-statement.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "2016-02-18"
     :association-rule/retrieved-at "2026-09-09"
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
      :note (str "cloud-itonami-assoc-6910-jpn-nichibenren (ADR-2607141700): "
                 (count (get catalog "nichibenren")) " nichibenren entries, each "
                 "with an official nichibenren.or.jp citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
