(require '[lib.stream :refer [cons-stream, stream-scale, add-streams, stream->list]])

(defn integral [integrand initial-value dt]
  #_{:clj-kondo/ignore [:inline-def]}
  (def i
    (cons-stream initial-value
                 #(add-streams (stream-scale integrand dt) i)))
  i)

(defn RC [R C dt]
  (fn [I V0]
    (add-streams (stream-scale I R) 
                 (integral (stream-scale I (/ 1.0 C)) V0 dt))))

(def RC1 (RC 5 1 0.5))

(def ones (cons-stream 1 (fn [] ones)))
(def integers (cons-stream 1 #(add-streams ones integers)))

(stream->list (RC1 integers 1) 10)

