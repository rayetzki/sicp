(ns chapter02.2.68_encode_haffman
  (:require [lib.haffman :refer [make-leaf, make-code-tree, encode, decode]]))

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree (make-leaf 'B 2)
                                  (make-code-tree  (make-leaf 'D 1)
                                                   (make-leaf 'C 1)))))
(def message '(A D A B B C A))

(encode sample-tree message)
(= (decode (encode sample-tree message) sample-tree) message)