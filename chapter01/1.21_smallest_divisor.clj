(ns chapter01.1.21_smallest_divisor
  (:require [lib.math :as math]))

(defn find-divisor [n test-divisor]
  (cond (> (math/square test-divisor) n) n
        (math/divides? test-divisor n) test-divisor
        :else (find-divisor n (inc test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= (smallest-divisor n) n))

;; (smallest-divisor 199) <-- 199 - is prime
;; (smallest-divisor 1999) <-- 1999 - is prime
;; (smallest-divisor 19999) <-- 7 - not prime
