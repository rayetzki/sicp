(require '[lib.math :refer [square]])

(defn random-in-range [low high]
  (let [int-range (- high low)]
    (+ low (rand int-range))))

(defn monte-carlo [trials experiment]
  (loop [remaining trials passed 0]
    (cond (zero? remaining) (/ passed trials)
          (true? (experiment)) (recur (dec remaining) (inc passed))
          :else (recur (dec remaining) passed))))

(defn is-in-circle? [[x1 x2] [y1 y2] radius]
  (<= (+ (square (random-in-range x1 x2))
         (square (random-in-range y1 y2)))
      (square radius)))

(defn estimate-integral [predicate trials [x1 x2] [y1 y2]]
  (* (monte-carlo trials #(predicate [x1 x2] [y1 y2] 1)) 4))

;; pi - circle with radius 1 and [-1 1] range
;; circle square - pi * radius ^ 2
;; more trials, means range is taken more often - pi is more precise
