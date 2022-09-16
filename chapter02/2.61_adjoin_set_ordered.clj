(ns chapter02.2.61_adjoin_set_ordered)

(defn element-of-set? [el items]
  (cond (empty? items) false
        (= el (first items)) true
        :else (element-of-set? el (rest items))))

(defn intersection-set [set1 set2]
  (if (or (empty? set1) (empty? set2)) (empty list)
      (let [x1 (first set1) x2 (first set2)]
        (cond (= x1 x2) (cons x1 (intersection-set (rest set1) (rest set2)))
              (< x1 x2) (intersection-set (rest set1) set2)
              (< x2 x1) (intersection-set set1 (rest set2))))))

(defn adjoin-set [el items]
  (let [[prefix suffix] (split-with #(< % el) items)]
    (if (= el (first suffix)) items
        (concat prefix (list el) suffix))))