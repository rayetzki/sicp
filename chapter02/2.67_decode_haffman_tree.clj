(require '[lib.haffman :refer [make-code-tree, make-leaf, decode]])

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree (make-leaf 'B 2)
                                  (make-code-tree  (make-leaf 'D 1)
                                                   (make-leaf 'C 1)))))

(def message '(0 1 1 0 0 1 0 1 0 1 1 1 0))

(decode message sample-tree) ;; (A D A B B C A) 

