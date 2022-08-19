(defn multiply [a b] (if (zero? b) 0 (+ a (multiply a (dec b)))))
(defn dbl [n] (multiply n 2))
(defn halve [n] (when (even? n) (/ n 2)))

(defn fast-mult-logn [a b]
  (letfn [(help [a b res]
            (cond
              (zero? b) res
              (even? b) (recur (dbl a) (halve b) res)
              (odd? b) (recur a (dec b) (+ res a))))]
    (help a b 0)))

(time (multiply 100 20000))
(time (fast-mult-logn 100 20000))