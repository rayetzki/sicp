(require '[lib.painter :refer [transform-picture, rotate270, rotate180, beside]])

(defn below-1 [p1 p2]
  (let [split [0 0.5]
        bottom (transform-picture p1 [0 0] [1 0] split)
        top (transform-picture p2 split [1 0.5] [0 1])]
    (fn [frame]
      (bottom frame)
      (top frame))))

(defn below-2 [p1 p2]
  (->> (beside (rotate270 p1) (rotate270 p2))
       (rotate270)
       (rotate180)))