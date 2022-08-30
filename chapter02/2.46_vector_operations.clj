(ns chapter02.2.46_vector_operations)

(defn add-vec [[x1 y1] [x2 y2]]
  (vector (+ x1 x2) (+ y1 y2)))

(defn sub-vec [[x1 y1] [x2 y2]]
  (vector (- x2 x1) (- y2 y1)))

(defn scale-vec [[x y] s]
  (vector (* s x) (* s y)))