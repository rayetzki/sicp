(ns chapter02.2.62_union_set_ordered)

(defn union-set [set1 set2]
  (cond (and (empty? set1) (empty? set2)) (empty list)
        (and (empty? set1) (not-empty set2)) set2
        (and (empty? set2) (not-empty set1)) set1
        :else (let [x1 (first set1) x2 (first set2)]
                (cond (= x1 x2) (cons x1 (union-set (rest set1) (rest set2)))
                      (< x1 x2) (cons x1 (union-set (rest set1) set2))
                      :else (cons x2 (union-set set1 (rest set2)))))))