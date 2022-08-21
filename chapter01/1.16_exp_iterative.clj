(ns chapter01.1.16_exp_iterative
  (:require [lib.math :refer [square, halve]]))

(defn expt-recur [b n]
  (if (zero? n) 
    1
    (* b (expt-recur b (dec n)))))

(defn expt-iter [a n]
  (loop [counter n product 1]
    (if (zero? counter)
      product
      (recur (dec counter) (* a product)))))

(defn fast-expt [base exp]
  (cond (zero? exp) 1 
        (even? exp) (square (fast-expt base (halve exp))) 
        (odd? exp) (* base (fast-expt base (dec exp)))))

(defn expt-log [a n]
  (loop [res 1 b a n n]
    (cond (zero? n) res
          (even? n) (recur res (square b) (halve n))
          (odd? n) (recur (* res b) n (dec n)))))

(time (expt-recur 2 100))
(time (expt-iter 2 5))
(time (fast-expt 2 1000))
(time (expt-log 2 1000))