(ns chapter01.1.43_multiple_application
  (:require [chapter01.1.42_compose :refer [compose]]))

(defn repeated [f n]
  (fn [x]
    (loop [res 0 times n]
      (if (zero? times)
        res
        (recur ((compose f f) x) (dec (dec times)))))))