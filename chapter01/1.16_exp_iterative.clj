(ns chapter01.1.16_exp_iterative
  (:require [lib.math :refer [square, halve]]))

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

(defn fast-expt [base exp]
  (cond
    (zero? exp) 1
    (even? exp) (square (fast-expt base (halve exp)))
    :else (* base (fast-expt base (dec exp)))))

(defn expt-log [a n]
   (letfn [(expt-log-counter [a b n]
           (cond 
              (zero? n) a
              (even? n) (recur a (square b) (halve n))
              (odd? n) (recur (* a b) b (dec n))))]
    (expt-log-counter 1 a n)))

(time (expt-recur 2 100))
(time (expt-iter 2 100))
(time (fast-expt 2 1000))
(time (expt-log 2 1000))