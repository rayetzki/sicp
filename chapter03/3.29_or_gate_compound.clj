(require '[lib.digital :refer [make-wire, inverter-delay, and-gate-delay, and-gate, inverter]])

(defn or-gate [a1 a2 output]
  (let [d (make-wire) 
        e (make-wire) 
        d' (make-wire) 
        e' (make-wire) 
        c (make-wire)]
    (and-gate a1 a1 d)
    (and-gate a2 a2 e)
    (inverter d d')
    (inverter e e')
    (and-gate d' e' c)
    (inverter c output)))

(def or-gate-delay (+ and-gate-delay inverter-delay and-gate-delay inverter-delay))