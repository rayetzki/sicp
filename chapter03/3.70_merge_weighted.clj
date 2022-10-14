(ns chapter03.3.70_merge_weighted
  (:require [lib.stream :refer [cons-stream, empty-stream?, stream-value, stream-next, stream-map, add-streams, stream-filter]]))

(def ones (cons-stream 1 (fn [] ones)))
(def integers (cons-stream 1 #(add-streams ones integers)))

(defn merge-weighted [s1 s2 weight]
  (cond (empty-stream? s1) s2
        (empty-stream? s2) s1
        :else (let [s1-val (stream-value s1) s2-val (stream-value s2)]
                (cond (< (weight s1-val) (weight s2-val)) (cons-stream s1-val #(merge-weighted (stream-next s1) s2 weight))
                      (> (weight s1-val) (weight s2-val)) (cons-stream s2-val #(merge-weighted s1 (stream-next s2) weight))
                      :else (cons-stream s1-val #(merge-weighted (stream-next s2) (stream-next s1) weight))))))

(defn weighted-pairs [s1 s2 weight]
  (cons-stream (list (stream-value s1) (stream-value s2))
               #(merge-weighted (stream-map (fn [x] (list (stream-value s1) x)) (stream-next s2))
                                (weighted-pairs (stream-next s1) (stream-next s2) weight)
                                weight)))

(def ^:private pos-ints (weighted-pairs integers integers #(+ (first %) (second %))))
(def ^:private filtered-pos-ints
  (stream-filter (fn [[i j]]
                    (not (or
                          (= (rem i 2) 0)
                          (= (rem i 3) 0)
                          (= (rem i 5) 0)
                          (= (rem j 2) 0)
                          (= (rem j 3) 0)
                          (= (rem j 5) 0))))
  (weighted-pairs integers integers (fn [[i j]] (+ (* 2 i) (* 3 j) (* 5 i j)))))) 
