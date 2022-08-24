(require '[lib.math :refer [square]])

(defn square-tree [tree]
  (map
   (fn [sub-tree]
     (if-not (list? sub-tree)
       (square sub-tree)
       (square-tree sub-tree)))
   tree))

(def tree (list 1 (list 2 (list 3 4) 5) (list 6 7)))
(square-tree tree)