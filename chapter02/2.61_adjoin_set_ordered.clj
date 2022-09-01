(ns chapter02.2.61_adjoin_set_ordered)

(defn element-of-set? [el items]
  (cond (empty? items) false
        (= el (first items)) true
        (< el (first items)) false
        :else (element-of-set? el (rest items))))

(defn intersection-set [set1 set2]
  (if (or (empty? set1) (empty? set2)) (empty list)
      (let [x1 (first set1) x2 (first set2)]
        (cond (= x1 x2) (cons x1 (intersection-set (rest set1) (rest set2)))
              (< x1 x2) (intersection-set (rest set1) set2)
              (< x2 x1) (intersection-set set1 (rest set2))))))

(defn adjoin-set [el items]
  (cond (or (element-of-set? el items) (neg-int? el)) items
        (or (zero? el) (empty? items)) (cons el items)
        (> el (last items)) (concat items (list el)) 
        (< el (first items)) (concat (list el) items)
        :else (cons (first items) (adjoin-set el (rest items)))))