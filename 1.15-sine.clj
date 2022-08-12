(defn cube [x] (* x x x))
(defn p [x] (- (* 3 x) (* 4 (cube x))))
(defn sine [angle]
  (if (not (> (abs angle) 0.1))
    angle
    (p (recur (/ angle 3.0)))))

;; (sine 12.15)
;; 1. How many times p is called? 
;;    First time + 5 times recursively + once in a final call = 7 times
;; 2. Order of growth:
;;    Space ->  O(logn)
;;    Complexity -> O(logn)