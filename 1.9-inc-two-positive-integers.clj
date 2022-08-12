(defn inc-first [a b]
  (if (zero? a) b (inc (inc-first (dec a) b))))

;; (inc-first 4 5)
;; (inc (inc-first 3 5))
;; (inc (inc (inc-first 2 5)))
;; (inc (inc (inc (inc-first 1 5))))
;; (inc (inc (inc (inc 5))))
;; (inc (inc (inc 6)))
;; (inc (inc 7))
;; (inc 8)
;; 9 <-- recursive

(defn inc-second [a b]
  (if (zero? a) b (inc-second (dec a) (inc b))))
;; (inc-second 4 5)
;; (inc-second 3 6)
;; (inc-second 2 7)
;; (inc-second 1 8)
;; (inc-second 0 9)
;; 9 <-- iterative

