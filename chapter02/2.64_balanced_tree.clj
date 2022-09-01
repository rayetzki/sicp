(ns chapter02.2.64_balanced_tree)

(defn make-tree [entry left right]
  (list entry left right))

(defn partial-tree [elements quantity]
  (if (zero? quantity) 
    (cons '. elements)
    (let [left-size (quot (dec quantity) 2)
          [left-tree & right-elements] (partial-tree elements left-size)
          right-size (- quantity (inc left-size))
          this-entry (first right-elements)
          [right-tree & remaining] (partial-tree (rest right-elements) right-size)]
      (cons (make-tree this-entry left-tree right-tree) remaining))))

(defn list->tree [elems]
  (first (partial-tree elems (count elems))))

(list->tree (list 1 3 5 7 9 11))

;;            5
;;          .   .
;;         1     9
;;        .     .  .
;;       3     7   11

;; Takes a middle el, counts left depth, right depth and recursively builds sub-trees for each
;; Than takes an entry point, left tree and right tree and produces a tree-like list
;; remaining is a trick to build recursive sub-trees -> empty list on final tree build