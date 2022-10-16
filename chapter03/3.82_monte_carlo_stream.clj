(require '[lib.stream :refer [cons-stream, stream-map, stream-scale, stream-next, stream-value, stream->list]])
(require '[lib.math :refer [square]])

(defn random-in-range [low high]
  (let [range (- high low)]
    (+ low (* (rand) range))))

(defn random-number-pairs [low1 high1 low2 high2]
  (cons-stream (list (random-in-range low1 high1) (random-in-range low2 high2))
               #(random-number-pairs low1 high1 low2 high2)))

(defn monte-carlo [experiment-stream passed failed]
  (letfn [(next [passed failed]
            (cons-stream (float (/ passed (+ passed failed)))
                         #(monte-carlo (stream-next experiment-stream) passed failed)))]
    (if (stream-value experiment-stream)
      (next (+ passed 1) failed)
      (next passed (+ failed 1)))))

(defn estimate-integral [p x1 x2 y1 y2]
  (let [area (* (- x2 x1) (- y2 y1))
        randoms (random-number-pairs x1 x2 y1 y2)]
    (stream-scale (monte-carlo (stream-map p randoms) 0 0) area)))

(defn sum-of-square [x y] (+ (square x) (square y)))
(def pi-stream (estimate-integral #(not (> (sum-of-square (- (first %) 1) (- (second %) 1)) 1)) 0 2 0 2))
(stream->list pi-stream 5000)