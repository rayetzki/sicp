(require '[chapter01.1.36_fixed_point_logn :refer [fixed-point]])
(require '[lib.math :refer [deriv, cube, square]])

(defn newtons-transform [g]
  (fn [x]
    (- x (/ (g x) ((deriv g) x)))))

(defn newtons-method [g guess]
  (fixed-point (newtons-transform g) guess))

(defn cubic [a b c]
  (fn [x]
    (+ (cube x) (* a (square x)) (* b x) c)))

(newtons-method (cubic 1 1 1) 1)