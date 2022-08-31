(require '[lib.painter :refer [transform-picture]])

(defn flip-horiz [p]
  (transform-picture p [1 0] [0 0] [1 1]))

(defn rotate [p]
  (transform-picture p [1 0] [1 1] [0 0]))

(defn rotate180 [p]
  (repeat 2 (rotate p)))

(defn rotate270 [p]
  (repeat 3 (rotate p)))