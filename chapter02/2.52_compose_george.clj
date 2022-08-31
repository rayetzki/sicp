(require '[lib.painter :refer [below, flip-horiz, flip-vert, rotate180, beside]])

(defn square-of-four [tl tr bl br]
  (fn [picture]
    (let [top (beside (tl picture) (tr picture))
          bottom (beside (bl picture) (br picture))]
      (below top bottom))))

(defn right-split [picture fraction]
  (if (zero? fraction)
    picture
    (let [smaller (right-split picture (dec fraction))]
      (beside picture (below smaller smaller)))))

(defn up-split [picture fraction]
  (if (zero? fraction)
    picture
    (let [smaller (up-split picture (dec fraction))]
      (below picture (beside smaller smaller)))))

(defn corner-split [picture fraction]
  (if (zero? fraction)
    picture
    (let [up (up-split picture (dec fraction))
          right (right-split picture (dec fraction))
          top-left (beside up up)
          bottom-right (below right right)
          top-right (corner-split picture (dec fraction))]
      (beside (below top-left picture)
              (below top-right bottom-right)))))

(def combine-four (square-of-four flip-horiz identity rotate180 flip-vert))

(defn square-limit [picture fraction]
  (combine-four (corner-split picture fraction)))