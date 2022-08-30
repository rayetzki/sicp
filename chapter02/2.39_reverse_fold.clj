(require '[lib.fold :refer [fold-left, fold-right]])

(defn reverse-right [collection]
  (fold-right #(concat %2 (list %1)) (empty collection) collection))
;; (concat (concat (concat (concat ()) (list 3)) (list 2)) (list 1))
;; (concat () (list 3) (list 2) (list 1))
;; (3 2 1)
(reverse-right (list 1 2 3 4 5 6))

(defn reverse-left [collection]
  (fold-left conj (empty collection) collection))

;; (loop (conj () 1) (2 3))
;; (loop (conj (1) 2))
;; (loop (conj (2 1) 3))
;; (loop (conj (3 2 1) ()))
;; (3 2 1)
(reverse-left (list 1 2 3))