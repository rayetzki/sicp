(require '[lib.math :refer [halve]])
(require '[chapter02.2.7_interval :refer [upper-bound, lower-bound, add-interval, make-interval, div-interval, mul-interval]])
(require '[chapter02.2.8_sub_interval :refer [sub-interval]])

(defn interval-width [interval]
  (halve (abs (- (upper-bound interval) (lower-bound interval)))))

(def A (make-interval 1 1))
(def B (make-interval 2 2.5))

(== (interval-width (add-interval A B)) (+ (interval-width B) (interval-width A))) ;; <-- true
(== (interval-width (sub-interval A B)) (- (interval-width B) (interval-width A))) ;; <-- true
(== (interval-width (mul-interval A B)) (* (interval-width A) (interval-width B))) ;; <-- false
(== (interval-width (div-interval A B)) (/ (interval-width A) (interval-width B))) ;; <-- false

