(require '[lib.math :refer [halve]])
(require '[chapter02.2.7_interval :refer [make-interval, lower-bound, upper-bound]])

(defn make-center-width [value tolerance]
  (make-interval (- value tolerance) (+ value tolerance)))

(defn center [interval]
  (halve (+ (lower-bound interval) (upper-bound interval))))

(defn width [interval]
  (halve (- (lower-bound interval) (upper-bound interval))))

(defn percent [interval] 
  (let [upper (upper-bound interval)
        lower (lower-bound interval)]
    (* (/ (- upper lower) (+ upper lower)) 100.0)))

(defn make-center-percent [value tolerance]
  (let [tolerance-val (/ (* value tolerance) 100.0)]
    (make-center-width value tolerance-val)))