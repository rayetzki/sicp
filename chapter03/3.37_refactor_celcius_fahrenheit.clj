(require '[lib.constraints :refer [set-value!, constant, make-connector, adder, multiplier, probe]])

(defn c+ [x y]
  (let [z (make-connector)]
    (adder x y z)
    z))

(defn c* [x y]
  (let [z (make-connector)]
    (multiplier x y z)
    z))

(defn cv [x]
  (let [z (make-connector)]
    (constant x z)
    z))

(defn c:- [x y]
  (let [z (make-connector)]
    (multiplier z x y)
    z))

(defn celcius-fahrenheit-converter [x]
  (c+ (c* (c:- (cv 9) (cv 5)) x) 
      (cv 32)))

(def C (make-connector))
(def F (celcius-fahrenheit-converter C))
(probe "Celcius: " C)
(probe "Fahrenheit: " F)
(set-value! C 24 'user)
