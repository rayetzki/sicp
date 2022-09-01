;; Same O(n) but, n could be larger due to duplicates
(defn element-of-set? [el S]
  (cond
    (empty? S) false
    (= el (first S)) true
    :else (element-of-set? el (rest S))))

;; O(1) instead of at max O(n) since no containment check done
(defn adjoin-set [el S] (cons el S))

;; O(n) instead of O(n^2) but with duplicates
(defn intersection-set [S1 S2]
  (filter #(element-of-set? % S2) S1))

;;  O(n), prev O(n^2), but with duplicates
(defn union-set [S1 S2]
  (reduce #(adjoin-set %2 %1) S1 S2))