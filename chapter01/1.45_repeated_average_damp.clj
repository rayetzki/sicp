(require '[chapter01.1.36_fixed_point_logn :refer [average-damp, fixed-point]])
(require '[chapter01.1.43_multiple_application :refer [repeated]])

(defn nth-root-damped [x nth]
  (fixed-point 
   ((repeated average-damp (Math/floor (/ (Math/log nth) (Math/log 2)))) (fn [y] (/ x (Math/pow y (dec nth))))) 1.0))

(nth-root-damped 16 4)

