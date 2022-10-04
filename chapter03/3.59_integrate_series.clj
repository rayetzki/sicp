(ns chapter03.3.59_integrate_series
  (:require [lib.stream :refer [stream-next, multiply-streams, stream-value, cons-stream, add-streams, stream-map, stream-map, divise-streams]]))

(def ones (cons-stream 1 (fn [] ones)))
(def integers (cons-stream 1 #(add-streams ones integers)))
(defn integrate-series [stream] (divise-streams stream integers))

(def var-one-series (cons-stream 1 #(stream-map (fn [x] (if (= x 1) -1 1)) var-one-series)))

(defn filter-odd [stream] (cons-stream (stream-value stream) #(filter-odd (stream-next (stream-next stream)))))
(defn filter-even [stream] (cons-stream (stream-value (stream-next stream)) #(filter-even (stream-next (stream-next stream)))))

(def exp-series (cons-stream 1 #(integrate-series exp-series)))
(def cosine-series (multiply-streams var-one-series (filter-odd exp-series)))
(def sine-series (multiply-streams var-one-series (filter-even exp-series)))