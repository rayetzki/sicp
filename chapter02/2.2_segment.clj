(ns chapter02.2.2_segment
  (:require [lib.math :refer [halve]])
  (:import java.lang.Math))

(defn make-point [x y] [x y])
(defn x-point [p] (first p))
(defn y-point [p] (last p))
(defn print-point [p]
  (format "(%f, %f)" (x-point p) (y-point p)))

(defn make-segment [p1 p2] [p1 p2])
(defn start-segment [seg] (first seg))
(defn end-segment [seg] (last seg))

(defn segment-length [seg] 
  (let [x1 (x-point (start-segment seg))
        x2 (x-point (end-segment seg))
        y1 (y-point (start-segment seg))
        y2 (y-point (end-segment seg))]
    (Math/hypot (- x2 x1) (- y2 y1))))

(defn midpoint-segment [seg] 
  (let [x1 (x-point (start-segment seg))
        x2 (x-point (end-segment seg))
        y1 (y-point (start-segment seg))
        y2 (y-point (end-segment seg))]
     (make-point (halve (- x2 x1)) (halve (- y2 y1)))))