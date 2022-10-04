(ns chapter03.3.61_invert_unit_series
  (:require [lib.stream :refer [cons-stream, stream-scale, stream-next]])
  (:require [chapter03.3.60_mul_series :refer [mul-series]]))

;; X = 1 - Sk*X

(defn invert-unit-series [series]
  (cons-stream 1 #(stream-scale (mul-series (stream-next series) (invert-unit-series series)) -1)))