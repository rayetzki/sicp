(ns chapter02.2.8_sub_interval
  (:require [chapter02.2.7_interval :refer [make-interval, upper-bound, lower-bound]]))

(defn sub-interval [x y]
  (make-interval (abs (- (lower-bound x) (lower-bound y)))
                 (abs (- (upper-bound x) (upper-bound y)))))

(sub-interval (make-interval 2 3) (make-interval 3 5))