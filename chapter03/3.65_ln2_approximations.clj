(require '[lib.stream :refer [stream-ref, stream-next, cons-stream, stream-map, stream-value, partial-sums, display-stream]])
(require '[lib.math :refer [square]])

(defn euler-transform [stream]
  (let [s0 (stream-ref stream 0) ; Sn −1
        s1 (stream-ref stream 1) ; Sn
        s2 (stream-ref stream 2)] ; Sn+1
    (cons-stream (- s2 (/ (square (- s2 s1)) (+ s0 (* -2 s1) s2)))
                 #(euler-transform (stream-next stream)))))

(defn make-tableau [transform stream]
  (cons-stream stream #(make-tableau transform (transform (transform stream)))))

(defn accelerated-sequence [transform stream]
  (stream-map stream-value (make-tableau transform stream)))

(defn ln-summands [n]
  (cons-stream (/ 1.0 n) #(stream-map - (ln-summands (inc n)))))

(def ln-stream 
  (partial-sums (ln-summands 1)))

(display-stream (accelerated-sequence euler-transform ln-stream))
;; constant value in 2-3 steps