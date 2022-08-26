(require '[lib.fold :refer [fold-right]])

(defn count-leaves [tree]
  (fold-right + 0 (map #(if (list? %) (count-leaves %) 1) tree)))

(count-leaves (list 1 2 () () (list 3 () ())))