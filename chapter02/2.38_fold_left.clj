(require '[lib.fold :refer [fold-right, fold-left]])

(fold-right / 1 (list 1 2 3))
(fold-left / 1 (list 1 2 4))

(fold-right list (empty list) (list 1 2 3))
(fold-left list (empty list) (list 1 2 3))