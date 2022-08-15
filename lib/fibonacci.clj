(ns lib.fibonacci 
  (:require [lib.math :as math]))


(defn fib-iter [a b p q count]
    (cond
      (zero? count) b
      (even? count) (fib-iter
                     a
                     b
                     (+ (math/square p) (math/square q))
                     (+ (* 2 p q) (math/square q))
                     (/ count 2))
      :else (fib-iter
             (+ (* b q) (* a q) (* a p))
             (+ (* b p) (* a q))
             p
             q
             (dec count))))

  (defn fibonacci [n]
    (fib-iter 1 0 0 1 n))