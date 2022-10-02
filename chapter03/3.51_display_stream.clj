(require '[lib.stream :refer [stream-ref, stream-map, stream-enumerate-interval]])

(defn show [x]
  (println x)
  x)

(def x (stream-map show (stream-enumerate-interval 0 10)))
(stream-ref x 5)
(stream-ref x 7)