(require '[lib.math :refer [square, average]])

(defn good-enough? [guess x] (< (abs (- (square guess) x)) 0.00000000001))
(defn improve [guess x] (average guess (/ x guess)))
(defn new-if [predicate then-clause else-clause]
  (cond predicate 
        then-clause
        :else else-clause))

(defn sqrt [x]
  (loop [guess 1.0]
    (if (good-enough? guess x)
      guess
      (recur (improve guess x)))))