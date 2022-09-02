(ns chapter02.2.69_generate_huffman_tree
  (:require [lib.haffman :refer [make-code-tree, make-leaf-set, adjoin-set]]))

(defn successive-merge [pairs-set]
  (loop [[first-leaf & leaves] pairs-set]
    (if (nil? (first leaves))
      first-leaf
      (recur (adjoin-set (make-code-tree first-leaf (first leaves)) (rest leaves))))))

(defn generate-huffman-tree [pairs]
  (successive-merge (make-leaf-set pairs)))

(def sample-pairs (list '(A 4) '(B 2) '(C 1) '(D 1)))

(generate-huffman-tree sample-pairs)