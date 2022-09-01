(ns chapter02.2.63_tree_list)

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (nth tree 2))

(defn tree [tree]
  (if (empty? tree)
    (empty tree)
    (concat (tree (left-branch tree))
            (cons (entry tree) (tree (right-branch tree))))))

(defn tree-2 [tree]
  (letfn [(help [tree result-list]
            (if (empty? tree)
              result-list
              (help (left-branch tree)
                    (cons (entry tree) (help (right-branch tree) result-list)))))]
    (help tree (empty tree))))