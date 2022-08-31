(ns escher.core
  (require [quil.core :as q]))

(def width 1200)
(def height 1200)

(def draw-line q/line)

(def whole-window {:origin [0 0]
                   :e1 [width 0]
                   :e2 [0 height]})

(def frame1 {:origin [200 50]
             :e1 [200 100]
             :e2 [150 200]})

(def frame2 {:origin [50 50]
             :e1 [100 0]
             :e2 [0 200]})

(defn add-vec [[x1 y1] [x2 y2]]
  ;; COMPLETE (Ex 2.46)
  [(+ x1 x2) (+ y1 y2)])

(defn sub-vec [[x1 y1] [x2 y2]]
  ;; COMPLETE
  [(- x1 x2) (- y1 y2)])

(defn scale-vec [[x y] s]
  ;; COMPLETE
  [(* x s) (* y s)])

(defn frame-coord-map
  [{:keys [origin e1 e2]}]
  (fn [[x y]]
    (add-vec origin
             (add-vec (scale-vec e1 x)
                      (scale-vec e2 y)))))

(defn frame-painter [{:keys [origin e1 e2]}]
  (let [corner (add-vec origin (add-vec e1 e2))]
    (draw-line origin (add-vec origin e1))
    (draw-line origin (add-vec origin e2))
    (draw-line (add-vec origin e2) corner)
    (draw-line (add-vec origin e1) corner)))

(defn segment-painter [segment-list]
  (fn [frame]
    (let [m (frame-coord-map frame)]
      (doseq [[start end] segment-list]
        (draw-line (m start) (m end))))))

(defn transform-picture [p origin e1 e2]
  (fn [frame]
    (let [new-coords (frame-coord-map frame)
          new-origin (new-coords origin)]
      (p {:origin new-origin
          :e1 (sub-vec (new-coords e1) new-origin)
          :e2 (sub-vec (new-coords e2) new-origin)}))))

(defn flip-vert [p]
  (transform-picture p [0 1] [1 1] [0 0]))

(defn flip-horiz [p]
  ;; COMPLETE (Ex 2.50)
  (transform-picture p [1 0] [0 0] [1 1]))

(defn rotate [p]
  ;; COMPLETE
  (transform-picture p [1 0] [1 1] [0 0]))

(defn rotate180 [p]
  (rotate (rotate p)))

(defn rotate270 [p]
  (rotate (rotate (rotate p))))

(defn beside [p1 p2]
  (let [split [0.5 0]
        left (transform-picture p1 [0 0] split [0 1])
        right (transform-picture p2 split [1 0] [0.5 1])]
    (fn [frame]
      (left frame)
      (right frame))))

(defn below [p1 p2]
  ; COMPLETE (Ex 2.51)
  (let [split [0 0.5]
        bottom (transform-picture p1 [0 0] [1 0] split)
        top (transform-picture p2 split [1 0.5] [0 1])]
    (fn [frame]
      (bottom frame)
      (top frame))))

(defn path [& [veclist]]
  (loop [result [] paths veclist]
    (if (empty? (rest paths))
      (conj result (first veclist))
      (let [new-path (vector (first paths) (second paths))]
        (recur (conj result new-path) (rest paths))))))

(defn quartet [p1 p2 p3 p4]
  (below (beside p1 p2)
         (beside p3 p4)))

(defn square-of-four [tl tr bl br]
  (fn [picture]
    (let [top (beside (tl picture) (tr picture))
          bottom (beside (bl picture) (br picture))]
      (below top bottom))))

(defn right-split [p n]
  (if (zero? n)
    p
    (let [smaller (right-split p (dec n))]
      (beside p (below smaller smaller)))))

;; (Ex 2.44)
(defn up-split [picture fraction]
  (if (zero? fraction)
    picture
    (let [smaller (up-split picture (dec fraction))]
      (below picture (beside smaller smaller)))))

;; (Ex 2.45)
"Should be able to do
    (def right-split (split beside below))
    (def up-split (split below beside)
  and replace the existing *-split fns"
(defn split [f g]
  (fn [picture fraction]
    (letfn [(help [painter times]
              (if (zero? fraction)
                picture
                (let [smaller (help painter (dec times))]
                  (f painter (g smaller smaller)))))]
      (help picture fraction))))

(defn corner-split [picture n]
  (if (zero? n)
    picture
    (let [up (up-split picture (dec n))
          right (right-split picture (dec n))
          top-left (beside up up)
          bottom-right (below right right)
          top-right (corner-split picture (dec n))]
      (beside (below top-left picture)
              (below top-right bottom-right)))))

(def combine-four (square-of-four flip-horiz
                                  identity
                                  rotate180
                                  flip-vert))

(defn square-limit [picture fraction]
  (combine-four (corner-split picture fraction)))

; Ex2.49, Make these shapes with segment-painter/path
(def box (segment-painter (path [[0 0] [0 1] [1 1] [1 0]])))
(def x (segment-painter [[[0 0] [1 1]] [[0 1] [1 0]]]))
(def diamond (segment-painter (path [[0 0.5] [0.5 1] [1 0.5] [0.5 0]])))
(def george (segment-painter [[[0 0.15] [0.2 0.4]]
                              [[0.2 0.4] [0.3 0.35]]
                              [[0.3 0.35] [0.4 0.35]]
                              [[0.35 0.35] [0.3 0.15]]
                              [[0.3 0.15] [0.4 0]]
                              [[0.6 0] [0.67 0.2]]
                              [[0.67 0.2] [0.65 0.35]]
                              [[0.65 0.35] [0.7 0.35]]
                              [[0.7 0.35] [1 0.6]]
                              [[1 0.8] [0.65 0.5]]
                              [[0.65 0.5] [0.75 1]]
                              [[0.6 1] [0.5 0.7]]
                              [[0.5 0.7] [0.4 1]]
                              [[0.25 1] [0.33 0.4]]
                              [[0.33 0.4] [0.25 0.5]]
                              [[0.25 0.5] [0.2 0.5]]
                              [[0.2 0.5] [0 0.3]]]))

(defn draw [picture]
  (picture whole-window))

(defn image-painter [img]
  (fn [{[ox oy] :origin
        [e1x e1y] :e1
        [e2x e2y] :e2}]
    (let [width (.width img)
          height (.height img)
          coords-map (frame-coord-map {[ox oy] :origin
                                       [e1x e1y] :e1
                                       [e2x e2y] :e2})]
      ; COMPLETE
     )))

(def diag (segment-painter [[[0 0] [1 1]]]))

(defn draw-image []
  (let [man (image-painter (q/load-image "data/man.gif"))
        bruce (image-painter (q/load-image "data/bruce.jpg"))
        angels (image-painter (q/load-image "data/angels.jpg"))]
    (q/stroke-weight 4)
    (q/background 255)
    ;; (frame-painter frame1) 
    ;; (draw x)   
    ;; (draw box)
    ;; (draw diamond)  
    ;; (george whole-window)  
    ;; (draw george) 
    ;; (draw (flip-vert george)) 
    ;; (draw (flip-vert george)) 
    ;; (draw (beside box box)) 
    ;; (draw (combine-four george))
    ;; (draw (beside (below george george)
    ;;               (flip-horiz (below george george))))
    ;; (draw (below (beside george (flip-horiz george))
    ;;              (beside george (flip-horiz george))))

    ; (draw ((square-of-four identity flip-vert
    ;;                        flip-horiz rotate)
    ;;        george))

    ; Needs image-painter  
    ;; (bruce frame1) 
    ;; (bruce frame2) 
    ;; (draw (beside george bruce))
    ;; (draw (corner-split bruce 4))
     (draw (square-limit bruce 3)) 
    ;; (draw (beside  bruce (below  bruce
    ;;                             george)))
    ))

(q/defsketch escher
  :title "Escher"
  :draw draw-image
  :size [width height])

(defn -main [])
