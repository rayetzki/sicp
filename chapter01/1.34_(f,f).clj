(require '[lib.math :refer [square]])

(defn f [g] (g 2))

(f square)  ;; <-- 4
(f (fn [x] (/ (+ x 1) (* 2 x))))  ;; <-- calculatest as usual for lambda
(f f)  ;; <-- error, number is not a function