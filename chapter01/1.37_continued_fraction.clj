(ns chapter01.1.37_continued_fraction)

(defn cont-frac [N D k]
  (letfn [(loop [res times] 
            (if (zero? times) 
              res 
              (loop (/ (N times) (+ res (D times))) (dec times))))]
    (loop (/ (N k) (D k)) (dec k))))

(cont-frac (fn [i] 1.0) (fn [i] 1.0) 10)

;; precision after 4 digit is hit when k >= 10