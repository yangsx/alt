(ns alt.core)

(defmacro alternatives
  "This macro chooses one of the alternative CLAUSES.
  Each alternative is a pair of TAG and FORM where the tag is
  a keyword. Each TAG should be unique and its order does not matter.

  The first clause is chosen unless there is a :final clause, which can
  be overriden by a :blessed one. The form for the :blessed tag is another
  tag in existence. The macro expands to the form of the chosen alternative.
  If you need more than one form, they have to be enclosed in a DO form."
  [& clauses]
  (assert (even? (count clauses)) "invalid syntax: must be in tag-body pairs.")
  (let [tags (map first (partition 2 clauses))
        tagset (set tags)
        clauses (apply hash-map clauses)
        blessed (clauses :blessed)]
    (assert (and (every? keyword? tags)
                 (= (count tags) (count tagset))
                 (if blessed
                   (tagset blessed)
                   true))
            "invalid syntax: tags must be keywords, unique and a blessed
 tag must exist.")
    (let [selected
          (cond
            blessed        (clauses blessed)
            (tagset :final) (clauses :final)
            :else           (clauses (first tags)))]
      `~selected)))
