(ns chapter02.2.46_vector_operations)

(defn make-vec [x y] [x y])

(defn add-vec [[x1 y1] [x2 y2]]
  (make-vec (+ x1 x2) (+ y1 y2)))

(defn sub-vec [[x1 y1] [x2 y2]]
  (make-vec (- x1 x2) (- y1 y2)))

(defn scale-vec [[x y] s]
  (make-vec (* s x) (* s y)))