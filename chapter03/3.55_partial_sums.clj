(require '[lib.stream :refer [cons-stream, stream-value, add-streams, stream-next, integers-from-n, display-stream]])

(defn partial-sums [stream]
  (cons-stream (stream-value stream)
               #(add-streams (stream-next stream) (partial-sums stream))))

(display-stream (partial-sums (integers-from-n 1))) ;; 1, 3, 6, 10, 15...

