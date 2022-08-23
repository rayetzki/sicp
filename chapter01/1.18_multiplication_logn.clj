(require '[lib.math :refer [halve]])

(defn multiply [a b] (if (zero? b) 0 (+ a (multiply a (dec b)))))
(defn dbl [n] (multiply n 2))

(defn fast-mult-logn [a b]
  (loop [a a b b res 0]
    (cond (zero? b) res 
          (even? b) (recur (dbl a) (halve b) res)
          :else (recur a (dec b) (+ res a)))))

(time (multiply 100 20000))
(time (fast-mult-logn 100 20000))