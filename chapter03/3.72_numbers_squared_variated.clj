(require '[chapter03.3.70_merge_weighted :refer [weighted-pairs, integers]])
(require '[lib.stream :refer [stream-next, stream-value, cons-stream]])
(require '[lib.math :refer [square]])

(defn weight [[i j]] (+ (square i) (square j)))

(def ordered-sum-of-squares (weighted-pairs integers integers weight))
 
(defn equiv-sum-squares-stream [stream]
  (let [[first-next second-next] [(stream-next stream) (stream-next (stream-next stream))]
        p1 (stream-value stream)
        p2 (stream-value first-next)
        p3 (stream-value second-next)]
    (if (= (weight p1) (weight p2) (weight p3))
      (cons-stream (list (weight p1) p1 p2 p3) #(equiv-sum-squares-stream (stream-next second-next)))
      (equiv-sum-squares-stream first-next))))
