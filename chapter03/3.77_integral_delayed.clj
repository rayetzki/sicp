(ns chapter03.3.77_integral_delayed
  (:require [lib.stream :refer [cons-stream, empty-stream?, empty-stream, stream-next, stream-value]]))

(defn integral [integrand initial-value dt]
  (let [integrand-stream (force integrand)]
    (cons-stream initial-value
                 #(if (empty-stream? integrand-stream)
                    empty-stream
                    (integral (stream-next integrand-stream)
                              (+ (* dt (stream-value integrand-stream)) initial-value)
                              dt)))))

