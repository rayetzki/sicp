(import 'lib.math)
(defn good-enough? [guess x] (< (abs (- (math/square guess) x)) 0.00000000001))
(defn improve [guess x] (math/average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))