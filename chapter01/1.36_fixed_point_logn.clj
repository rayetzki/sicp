(ns chapter01.1.36_fixed_point_logn
  (:import java.lang.Math)
  (:require [lib.math :refer [average]]))

(defn average-damp [f] 
  (fn [x]
    (average x (f x))))

(defn fixed-point [f val]
  (let [tolerance 0.01]
    (letfn [(close-enough? [v1 v2] (< (abs (- v1 v2)) tolerance))
            (try-it [guess times]
              (let [next (f guess)]
                (println "Trying: " guess)
                (if (close-enough? guess next)
                  (println "Fixed point:" next "." "Steps:" times)
                  (try-it next (inc times)))))]
      (try-it val 0))))

(def try-num 1.5)
(fixed-point (fn [x] (* 0.5 (+ (Math/log x) (/ (Math/log 1000) (Math/log x))))) try-num)
(fixed-point (fn [x] (/ (Math/log 1000) (Math/log x))) try-num)

;; acceleration by half speeds up calculation twice