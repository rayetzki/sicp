(ns chapter01.1.37_continued_fraction)

(defn cont-frac [N D k]
  (loop [res (/ (N k) (D k)) times (dec k)]
    (if (zero? times)
      res
      (recur (/ (N times) (+ res (D times))) (dec times)))))

(cont-frac (fn [i] 1.0) (fn [i] 1.0) 10)

;; precision after 4 digit is hit when k >= 10