(defn equal? [s1 s2]
  (if (and (list? s1) (list? s2))
    (cond
      (and (empty? s1) (empty? s2)) true
      (or (empty? s1) (empty? s1)) false
      (and (equal? (first s1) (first s2))
           (equal? (rest s1) (rest s2))) true
      :else false)
    (= s1 s2)))