(require '[lib.math :refer [halve]])

(defn multiply [a b] (if (zero? b) 0 (+ a (multiply a (dec b)))))
(defn dbl [n] (multiply n 2))

(defn fast-mult-rec [a b]
  (cond (= a 1) b
        (= b 1) a
        (even? b) (dbl (fast-mult-rec a (halve b)))
        :else (+ a (fast-mult-rec a (dec b)))))

(time (multiply 100 200))
(time (fast-mult-rec 100 200))