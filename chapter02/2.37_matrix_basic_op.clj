(ns chapter02.2.37_matrix_basic_op
  (:require [lib.fold :refer [fold-right, acc-n]]))

(defn dot-product [v w]
  (fold-right + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map #(dot-product % v) m))

(defn transpose [mat]
  (acc-n cons (empty mat) mat))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector cols %)  m)))

(transpose [[1 0 -2] [0 3 -1] [1 2 1]])
(matrix-*-vector [[1 0 -2] [0 3 -1] [1 2 1]] [3 -1 4])
(matrix-*-matrix [[1 0 -2] [0 3 -1] [1 2 1]] [[3 -1 4] [2 1 1]])