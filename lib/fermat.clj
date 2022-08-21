(ns lib.fermat
  (:require [lib.math :refer [square, halve]]))

(defn expmod [base exp m]
  (cond
    (zero? exp) 1 
    (even? exp) (mod (square (expmod base (halve exp) m)) m) 
    :else (mod (* base (expmod base (dec exp) m)) m)))

(defn fermat-test [n exp-calc]
  (letfn [(try-it [a]
    (= (exp-calc a n n) a))]
     (try-it (inc (rand-int (dec n))))))

(defn fast-prime? [n times exp-calc]
  (cond (zero? times) true
        (fermat-test n exp-calc) (fast-prime? n (dec times) exp-calc)
        :else false))