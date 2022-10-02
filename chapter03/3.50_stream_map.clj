(require '[lib.stream :refer [empty-stream?, empty-stream, stream-value, stream-next, cons-stream, stream-ref]])

(defn stream-map [proc & argstreams]
  (if (empty-stream? (first argstreams))
    empty-stream
    (cons-stream
     (apply proc (map stream-value argstreams))
     #(apply stream-map 
            (cons proc (map stream-next argstreams))))))