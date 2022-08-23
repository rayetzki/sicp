(require '[chapter02.2.7_interval :refer [mul-interval, make-interval, upper-bound, lower-bound]])

(defn div-interval [x y]
  (if (contains? y 0)
    (throw (AssertionError. "Divisible interval cannot include 0")) 
    (mul-interval x (make-interval (/ 1.0 (upper-bound y)) (/ 1.0 (lower-bound y))))))

(div-interval (make-interval 1.1 1.3) (make-interval 0 0.5))