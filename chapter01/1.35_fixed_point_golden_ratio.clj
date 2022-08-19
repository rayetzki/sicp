(def tolerance 0.00001)

(defn fixed-point [f val]
  (letfn [(close-enough? [v1 v2]
            (< (abs (- v1 v2)) tolerance))
          (try-it [guess]
               (let [next (f guess)]
                 (if (close-enough? guess next) 
                   next
                   (try-it next))))]
    (try-it val)))

(fixed-point (fn [x] (+ 1 (/ 1 x))) 3.0)