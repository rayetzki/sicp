(require '[lib.constraints :refer [make-connector, probe, multiplier]])

(defn squarer [a b]
  (multiplier a a b))

(def a (make-connector))
(def b (make-connector))
(squarer a b)
(probe "A" a)
(probe "B" b)

;; Will not compute backwards -> square root of b, if a is not set extra. Doesn't meet a requirment of constraint