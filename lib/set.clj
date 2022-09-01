(ns lib.set)

(defn element-of-set? [el S]
  (cond
    (empty? S) false
    (= el (first S)) true
    :else (element-of-set? el (rest S))))

(defn adjoin-set [el S]
  (if (element-of-set? el S)
    S
    (cons el S)))

(defn intersection-set [S1 S2]
  (filter #(element-of-set? % S2) S1))

(defn union-set [S1 S2]
  (reduce #(adjoin-set %2 %1) S1 S2))