(ns chapter01.1.42_compose
  (:require [lib.math :refer [square]]))

(defn compose [f g]
  (fn [x] 
    (f (g x))))

((compose square inc) 6)