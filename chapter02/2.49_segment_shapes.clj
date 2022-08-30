(require '[chapter02.2.46_vector_operations :refer [add-vec, scale-vec]])

(defn draw-line [_ __] identity)

(defn frame-coord-map
  [{:keys [origin e1 e2]}]
  (fn [[x y]]
    (add-vec origin
             (add-vec (scale-vec e1 x)
                      (scale-vec e2 y)))))

(defn segment-painter [segment-list]
  (fn [frame]
    (let [m (frame-coord-map frame)]
      (doseq [[start end] segment-list]
        (draw-line (m start) (m end))))))

(def box
  (segment-painter [[[0 0] [0 1]]
                    [[0 1] [1 1]]
                    [[1 1] [1 0]]
                    [[1 0] [0 0]]]))

(def X
  (segment-painter [[[0 0] [1 1]]
                    [[0 1] [1 0]]]))

(def diamond
  (segment-painter [[[0 0.5] [0.5 1]]
                    [[0.5 1] [1 0.5]]
                    [[1 0.5] [0.5 0]]
                    [[0.5 0] [0 0.5]]]))

(def george
  (segment-painter [[[0 0.15] [0.2 0.4]]
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
