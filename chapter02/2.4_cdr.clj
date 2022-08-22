(defn cons [x y]
  (fn [m] (m x y)))
(defn car [z]
  (z 
   (fn [p q] p)))
(defn cdr [z]
  (z
   (fn [p q] q)))

(car (cons 1 2))
(cdr (cons 1 2))
