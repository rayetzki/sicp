(require '[lib.stream :refer [stream-merge, cons-stream, stream-scale, stream-ref]])

(def S (cons-stream 1 #(stream-merge (stream-merge (stream-scale S 3) (stream-scale S 2))
                                     (stream-scale S 5))))

(stream-ref S 3)