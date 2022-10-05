(require '[lib.stream :refer [stream-ref, stream-next, stream-map, cons-stream]])
(require '[lib.math :refer [average]])

(defn good-enough? [stream tolerance] 
  (< (abs (- (stream-ref stream 1) (stream-ref stream 0))) tolerance))
(defn improve [guess x] (average guess (/ x guess)))

(defn sqrt-stream [x]
  (def guesses
    (cons-stream 1.0 #(stream-map (fn [guess] (improve guess x)) guesses)))
  guesses)

(defn stream-limit [stream tolerance]
  (if (good-enough? stream tolerance)
    (max (stream-ref stream 1) (stream-ref stream 0))
    (stream-limit (stream-next stream) tolerance)))

(defn sqrt [x tolerance]
  (stream-limit (sqrt-stream x) tolerance))