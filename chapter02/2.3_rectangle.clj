(ns chapter02.2.3_rectangle
  (:require [chapter02.2.2_segment :refer [segment-length, make-segment, make-point]]))

(defn is-rectangle? [A B C D] 
  (= 
   (segment-length (make-segment A B))
   (segment-length (make-segment C D))))

(defn rec-perimeter [A B C D]
  (+
   (segment-length (make-segment A B))
   (segment-length (make-segment B C))
   (segment-length (make-segment C D))
   (segment-length (make-segment A D))))

(defn rec-square [A B C D] 
  (*
   (segment-length (make-segment A B))
   (segment-length (make-segment B C))))

(def A (make-point -3 -3))
(def B (make-point -3 2))
(def C (make-point 4 2))
(def D (make-point 4 -3))

(rec-square A B C D)
(rec-perimeter A B C D)