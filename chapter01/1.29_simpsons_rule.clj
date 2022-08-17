(ns chapter01.1.29_simpsons_rule
  (:require [lib.math :refer [halve, cube]])
  (:require [lib.sum :refer [sum-linear-rec]]))

(defn integral [f a b dx sum-fn]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum-fn f (+ a (halve dx)) add-dx b) dx)))

(defn simpsons-integral [f a b n sum-fn] 
  (let [h (/ (- b a) n)
        y (fn [k] (f (+ a (* k h))))
        step (fn [i] (+ i 2))]
    (double (* (/ h 3) 
             (+ (f a) 
                (* 4 (sum-fn y 1 step n)) 
                (* 2 (sum-fn y 2 step (dec n))))))))

(integral cube 0 1 0.001 sum-linear-rec)
(simpsons-integral cube 0 1 1000 sum-linear-rec)
