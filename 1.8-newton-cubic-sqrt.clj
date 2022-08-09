(defn square [x] (* x x))
(defn cube [x] (* x x x))
(defn good-enough? [guess x] (< (abs (- (cube guess) x)) 0.00000000001))
(defn improve [guess x] (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(defn cube-iter [guess x]
  (if (good-enough? guess x)
    guess
    (cube-iter (improve guess x) x)))

(defn cbrt [x] (cube-iter 1.0 x))

(cbrt 27)