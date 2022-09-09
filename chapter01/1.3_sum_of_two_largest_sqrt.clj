(defn sum-of-square [x y] (+ (* x x) (* y y)))

(defn sum-of-two-largest-squared [x y z]
  (cond (and (< x y) (< x z)) (sum-of-square y z)
        (and (< y z) (< y x)) (sum-of-square x z)
        :else (sum-of-square x y)))

(sum-of-two-largest-squared 2 4 6)