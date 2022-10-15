(require '[lib.stream :refer [stream-next, stream-map]])
(require '[lib.math :refer [halve]])

(defn sign-change-detector [a b]
  (cond (and (<= a 0) (pos? b)) 1
        (and (>= a 0) (neg? b)) -1
        :else 0))

(defn smooth [stream]
  (stream-map #(halve (+ %1 %2)) stream (stream-next stream)))

(defn zero-crossings [input-stream]
  (stream-map sign-change-detector input-stream (stream-next input-stream)))

(defn make-zero-crossings [sense-data]
  (zero-crossings (smooth sense-data)))