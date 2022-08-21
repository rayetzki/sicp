(ns chapter01.1.43_multiple_application
  (:require [chapter01.1.42_compose :refer [compose]]))

(defn repeated [f n]
  (fn [x]
    (letfn [(loop [res times]
              (if (zero? times)
                res
                (loop ((compose f f) x) (dec (dec times)))))]
      (loop 0 n))))