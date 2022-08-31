(ns lib.painter)

(defn make-vec [x y] [x y])

(defn add-vec [[x1 y1] [x2 y2]]
  (make-vec (+ x1 x2) (+ y1 y2)))

(defn sub-vec [[x1 y1] [x2 y2]]
  (make-vec (- x1 x2) (- y1 y2)))

(defn scale-vec [[x y] s]
  (make-vec (* s x) (* s y)))

(def draw-line identity)

(defn path [& [veclist]]
  (loop [result [] paths veclist]
    (if (empty? (rest paths))
      (conj result (first veclist))
      (let [new-path (make-vec (first paths) (second paths))]
        (recur (conj result new-path) (rest paths))))))

(defn frame-coord-map
  [{:keys [origin e1 e2]}]
  (fn [[x y]]
    (add-vec origin
             (add-vec (scale-vec e1 x)
                      (scale-vec e2 y)))))

(defn transform-picture [p origin e1 e2]
  (fn [frame]
    (let [new-coords (frame-coord-map frame)
          new-origin (new-coords origin)]
      (p {:origin new-origin
          :e1 (sub-vec (new-coords e1) new-origin)
          :e2 (sub-vec (new-coords e2) new-origin)}))))

(defn segment-painter [segment-list]
  (fn [frame]
    (let [relative-coords (frame-coord-map frame)]
      (doseq [[start end] segment-list]
        (draw-line (relative-coords start) (relative-coords end))))))

(defn beside [p1 p2]
  (let [split [0.5 0]
        left (transform-picture p1 [0 0] split [0 1])
        right (transform-picture p2 split [1 0] [0.5 1])]
    (fn [frame]
      (left frame)
      (right frame))))

(defn below [p1 p2]
  (let [split [0 0.5]
        bottom (transform-picture p1 [0 0] [1 0] split)
        top (transform-picture p2 split [1 0.5] [0 1])]
    (fn [frame]
      (bottom frame)
      (top frame))))

(defn flip-horiz [p]
  (transform-picture p [1 0] [0 0] [1 1]))

(defn flip-vert [p]
  (transform-picture p [0 1] [1 1] [0 0]))

(defn rotate [p]
  (transform-picture p [1 0] [1 1] [0 0]))

(defn rotate180 [p]
  (repeat 2 (rotate p)))

(defn rotate270 [p]
  (repeat 3 (rotate p)))