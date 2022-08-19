(import java.lang.Math)

(def tolerance 0.01)

(defn fixed-point [f val]
  (letfn [(close-enough? [v1 v2] 
                         (< (abs (- v1 v2)) tolerance))
          (try-it [guess times] 
            (let [next (f guess)] 
              (println "Trying: " guess)
              (if (close-enough? guess next)
                (println "Fixed point:"next".""Steps:"times)
                (try-it next (inc times)))))]
    (try-it val 0)))

(def try-num 1.5)
(fixed-point (fn [x] (* 0.5 (+ (Math/log x) (/ (Math/log 1000) (Math/log x))))) try-num)
(fixed-point (fn [x] (/ (Math/log 1000) (Math/log x))) try-num)

;; acceleration by half speeds up calculation twice