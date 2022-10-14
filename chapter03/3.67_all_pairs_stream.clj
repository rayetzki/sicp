(require '[lib.stream :refer [cons-stream, stream-value, stream-next, stream-map, stream-interleave]])

(defn pairs [s t]
  (cons-stream (list (stream-value s) (stream-value t))
               #(stream-interleave (stream-map (fn [x] (list (stream-value s) x)) (stream-next t))
                                   (pairs (stream-next s) t))))