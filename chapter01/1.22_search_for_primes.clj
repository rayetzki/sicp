(ns chapter01.1.22_search_for_primes
  (:import java.lang.System)
  (:require [chapter01.1.21_smallest_divisor :refer [prime?]])
  (:require [lib.fermat :refer [expmod]]))

(defn report-prime [n elapsed-time]
  (hash-map n elapsed-time))

(defn start-prime-test [n start-time prime-fn exp-calc]
  (when (prime-fn n exp-calc)
    (report-prime n (- (System/nanoTime) start-time))))

(defn timed-prime-test [n]
  (start-prime-test n (System/nanoTime) prime? expmod))

(defn search-for-primes [start prime-count prime-test]
  (loop [number (inc start) acc {}]
    (cond
      (= (count acc) prime-count) acc
      :else (recur (+ number 2) (conj acc (prime-test number))))))

(search-for-primes 1000 3 timed-prime-test)
