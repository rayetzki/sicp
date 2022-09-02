(ns chapter02.2.68_encode_haffman
  (:require [lib.haffman :refer [make-leaf, make-code-tree, leaf?, symbols, left-branch, right-branch, decode]])
  (:require [chapter02.2.61_adjoin_set_ordered :refer [element-of-set?]]))

(defn encode-symbol [tree sym]
  (loop [node tree bits (empty tree)]
    (if (leaf? node)
      (reverse bits)
      (let [left (left-branch node) right (right-branch node)
            next-branch? #(->> % symbols (element-of-set? sym))]
        (cond (next-branch? left) (recur left (cons 0 bits))
              (next-branch? right) (recur right (cons 1 bits))
              :else (throw (Exception. "No symnol in the tree" sym)))))))

(defn encode [tree message]
  (loop [[head & tail] message encoded-message (empty tree)]
    (if (nil? head)
      encoded-message
      (recur tail (concat encoded-message (encode-symbol tree head))))))

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree (make-leaf 'B 2)
                                  (make-code-tree  (make-leaf 'D 1)
                                                   (make-leaf 'C 1)))))
(def message '(A D A B B C A))

(encode sample-tree message)
(= (decode (encode sample-tree message) sample-tree) message)