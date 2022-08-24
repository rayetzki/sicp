(require '[lib.math :refer [cube]])

(defn tree-map [f tree]
  (map
   (fn [sub-tree]
     (if-not (list? sub-tree)
       (f sub-tree)
       (tree-map f sub-tree)))
   tree))

(def tree (list 1 (list 2 (list 3 4) 5) (list 6 7)))
(tree-map cube tree)