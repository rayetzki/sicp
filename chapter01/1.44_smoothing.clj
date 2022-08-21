(require '[lib.math :refer [cube]])
(require '[chapter01.1.43_multiple_application :refer [repeated]])

(defn smooth [f]
  (let [dx 0.0001]
    (fn [x]
      (/ (+ (f (- x dx)) (f x) (f (+ x dx))) 3))))

(defn smooth-repeated [f times]
  ((repeated smooth times) f))

((smooth cube) 2)
((smooth-repeated cube 10) 5.111)