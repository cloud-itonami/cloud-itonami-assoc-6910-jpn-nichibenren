# cloud-itonami-assoc-6910-jpn-nichibenren

Industry self-regulatory rule catalog for the **Japan Federation of Bar
Associations** (日本弁護士連合会 / JFBA, Nichibenren) — an 11th
industry-association-level source, and the FIRST aligned to ISIC 6910
(legal activities), alongside
[`cloud-itonami-assoc-6419-jpn-zenginkyo`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-jpn-zenginkyo),
[`cloud-itonami-assoc-6512-jpn-sonpo`](https://github.com/cloud-itonami/cloud-itonami-assoc-6512-jpn-sonpo),
[`cloud-itonami-assoc-6612-jpn-jsda`](https://github.com/cloud-itonami/cloud-itonami-assoc-6612-jpn-jsda),
[`cloud-itonami-assoc-6419-deu-bankenverband`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-deu-bankenverband),
[`cloud-itonami-assoc-6612-usa-finra`](https://github.com/cloud-itonami/cloud-itonami-assoc-6612-usa-finra),
[`cloud-itonami-assoc-6512-usa-naic`](https://github.com/cloud-itonami/cloud-itonami-assoc-6512-usa-naic),
[`cloud-itonami-assoc-6920-jpn-jicpa`](https://github.com/cloud-itonami/cloud-itonami-assoc-6920-jpn-jicpa),
[`cloud-itonami-assoc-6920-usa-aicpa`](https://github.com/cloud-itonami/cloud-itonami-assoc-6920-usa-aicpa),
[`cloud-itonami-assoc-6419-fra-fbf`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-fra-fbf),
and
[`cloud-itonami-assoc-6511-jpn-seiho`](https://github.com/cloud-itonami/cloud-itonami-assoc-6511-jpn-seiho).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on JFBA's behalf.

Coverage is reported honestly (see `association.facts/coverage`): an
entry not in `catalog` has **no spec-basis**, full stop — never
fabricate one.

## Data

- `data/datascript-tx.edn` — the catalog. Query this alongside other
  `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`.
- `src/association/facts.cljk` — the same catalog as a Clojure map, with
  `spec-basis` / `coverage` / `by-topic` over it.
- `src/association_facts.kotoba` — the same catalog as Kotoba, reaching
  the oracle, wasm and both native ISAs. **Generated** — do not hand-edit:

  ```
  kbb --backend sci scripts/gen_association_facts_kotoba.cljk           # regenerate
  kbb --backend sci scripts/gen_association_facts_kotoba.cljk --check   # exit 1 if stale
  ```

- `schema/association-rule.edn` — DataScript schema.

The test suite is hermetic, so it cannot tell you whether the cited documents
are still where JFBA put them. That check is a separate, network-touching
script:

```
kbb --backend sci scripts/verify_citations.cljk   # 0 all 2xx / 1 some are not / 2 REFUSED
```

`association.facts-test` asserts the `.cljc` and the data file agree field
for field, and `association-facts-kotoba-parity-test` compares every field
of every entry in the `.kotoba` against the `.cljc`, so the three readings
cannot drift apart without the suite saying so.

## Provenance

Eleven entries, every one citing an official `www.nichibenren.or.jp` URL.
Each PDF was fetched and its enactment and most recent amendment dates read
off the document's own cover page, which is where JFBA prints them — the
strictest tier this family uses.

- **2026-07-15** — Basic Rules on the Duties of Practicing Attorneys
  (enacted 2004-11-10, last amended 2021-06-11, English translation dated
  June 2022) from the PDF cover page; the organization-profile page
  (established 1949-09-01) by WebFetch.
- **2026-09-09** — the remaining nine, taken from the two pages that
  enumerate JFBA's English-language rules,
  [`/en/about/us/regulations.html`](https://www.nichibenren.or.jp/en/about/us/regulations.html)
  (JFBA Rules and Regulations) and
  [`/en/about/us/policies.html`](https://www.nichibenren.or.jp/en/about/us/policies.html).
  Each URL was re-fetched and answered `200 application/pdf`.

Where a cover page prints no amendment — the profile page, and the 2016
Mission Statement — `:association-rule/last-revised-date` is **absent**
rather than guessed.

JFBA labels these English texts unofficial and tentative translations. The
citation, dates and rule numbers are theirs; the Japanese text governs.

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Rule text itself
remains JFBA's; this repo stores only citation metadata (id/title/url/
dates), not full rule text.
