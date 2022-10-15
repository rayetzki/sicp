(require '[lib.stream :refer [stream-next, cons-stream, stream-value]])
(require '[lib.math :refer [halve]])

(defn sign-change-detector [a b]
  (cond (and (<= a 0) (pos? b)) 1
        (and (>= a 0) (neg? b)) -1
        :else 0))

(defn make-zero-crossings [input-stream last-value last-avpt]
  (let [avpt (halve (+ (stream-value input-stream) last-value))]
    (cons-stream (sign-change-detector avpt last-avpt)
                 #(make-zero-crossings (stream-next input-stream) (stream-value input-stream) avpt))))