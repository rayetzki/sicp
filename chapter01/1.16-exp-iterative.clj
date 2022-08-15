(defn expt-recur [b n]
  (if (zero? n) 
    1
    (* b (expt-recur b (dec n)))))

(defn expt-iter [a n]
   (letfn [(expt-counter [x counter product]
            (if (zero? counter)
              product
              (expt-counter x (dec counter) (* product x))))]
     (expt-counter a n 1)))

(defn square [x] (* x x))
(defn fast-expt [a n]
  (cond (zero? n) 1
        (even? n) (square (fast-expt a (/ n 2)))
        :else (* a (fast-expt a (dec n)))))

(defn expt-log [a n]
   (letfn [(expt-log-counter [a b n]
           (cond 
              (zero? n) a
              (even? n) (recur a (square b) (/ n 2))
              (odd? n) (recur (* a b) b (dec n))))]
    (expt-log-counter 1 a n)))

(time (expt-recur 2 100))
(time (expt-iter 2 100))
(time (fast-expt 2 1000))
(time (expt-log 2 1000))