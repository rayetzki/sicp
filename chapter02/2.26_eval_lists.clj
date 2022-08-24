(def x (list 1 2 3))
(def y (list 4 5 6))

(concat x y) ;; shallow list combination
(cons x y)   ;; appends a list as a first element to 
(list x y)   ;; list of 2 lists
