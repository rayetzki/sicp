(ns chapter03.3.66_pairs_interleave
  (:require [lib.stream :refer [cons-stream, stream-value, stream-next, stream-map, stream-interleave]]))

(defn pairs [s t]
  (cons-stream
   (list (stream-value s) (stream-value t))
   #(stream-interleave (stream-map (fn [x] (list (stream-value s) x)) (stream-next t))
                       (pairs (stream-next s) (stream-next t)))))

;; (1, 100) - 19 steps to achieve
;; (100, 100) - 2 ** 100 - 1 to achieve