(require '[lib.math :as math])

(defn good-enough? [guess x] 
  (< (abs (- (math/cube guess) x)) 0.00000000001))
(defn improve [guess x] 
  (/ (+ (/ x (math/square guess)) (* 2 guess)) 3))

(defn cbrt [x] 
  (loop [guess 1.0]
    (if (good-enough? guess x)
      guess
      (recur (improve guess x)))))

(cbrt 27)