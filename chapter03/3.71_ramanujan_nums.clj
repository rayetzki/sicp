(require '[chapter03.3.70_merge_weighted :refer [weighted-pairs, integers]])
(require '[lib.stream :refer [stream-next, stream-value, stream-map, stream-filter, stream-ref]])
(require '[lib.math :refer [cube]])

(defn ramanujan-weight [[i j]] (+ (cube i) (cube j)))

(def ordered-sum-of-cubes (weighted-pairs integers integers ramanujan-weight))

(def ramanujan-stream
  (stream-map ramanujan-weight
              (stream-filter #(= (ramanujan-weight %) (ramanujan-weight (stream-value (stream-next ordered-sum-of-cubes))))
                             ordered-sum-of-cubes)))

(stream-ref ramanujan-stream 1)