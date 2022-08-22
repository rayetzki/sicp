(import java.lang.Math)

(defn cons [a b]
  (* (Math/pow 2 a) (Math/pow 3 b)))

(defn car [x]
  (loop [x x res 0]
    (if (zero? (rem x 2))
      (recur (/ x 2) (inc res))
      res)))

(defn cdr [x]
  (loop [x x res 0]
    (if (zero? (rem x 3))
      (recur (/ x 3) (inc res))
      res)))


(cdr (cons 3 18))