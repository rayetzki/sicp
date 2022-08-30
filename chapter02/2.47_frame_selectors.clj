(defn make-frame--list [origin edge1 edge2]
  (list origin edge1 edge2))

(defn make-frame--cons [origin edge1 edge2]
  (cons origin (list edge1 edge2)))

(defn sel-origin [frame] (first frame))
(defn sel-edge1 [frame] (second frame))
(defn sel-edge2 [frame] (last frame))
