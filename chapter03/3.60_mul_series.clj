(ns chapter03.3.60_mul_series
  (:require [lib.stream :refer [stream-next, stream-scale, stream-value, cons-stream, add-streams]]))

(defn mul-series [s1 s2]
  (cons-stream (* (stream-value s1) (stream-value s2))
               #(add-streams (stream-scale (stream-next s2) (stream-value s1))
                             (mul-series (stream-next s1) s2))))