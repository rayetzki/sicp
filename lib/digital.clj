(ns lib.digital)

(def make-wire identity)
(def get-signal identity)
(def set-signal! identity)
(def inverter-delay 1000)
(def and-gate-delay 1000)
(def or-gate-delay 1000)

(defn logical-not [signal]
  (cond (= signal 0) 1
        (= signal 1) 0
        :else (throw (IllegalArgumentException. "Signal is incorrect" signal))))

(defn logical-and [s1 s2]
  (cond (and (= s1 1) (s2 1)) 1
        (and (or (= s1 0) (= s1 1)) (or (= s2 0) (= s2 1))) 0
        :else (throw (IllegalArgumentException. "Signal is incorrect" s1 s2))))

(defn logical-or [s1 s2]
  (cond (or (= s1 1) (= s2 1)) 1
        (and (or (= s1 0) (= s1 1)) (or (= s2 0) (= s2 1))) 0
        :else (throw (IllegalArgumentException. "Signal is incorrect" s1 s2))))

(defn inverter [input output]
  (letfn [(invert-input []
            @(future
               (Thread/sleep inverter-delay)
               (set-signal! output (logical-not (get-signal input)))))]
    (add-watch input :inverter invert-input)))

(defn and-gate [a1 a2 output]
  (letfn [(and-action []
            @(future
               (Thread/sleep and-gate-delay)
               (set-signal! output (logical-and (get-signal a1) (get-signal a2)))))]
    (add-watch a1 :and and-action)
    (add-watch a2 :and and-action)))

(defn or-gate [a1 a2 output]
  (letfn [(or-action []
            @(future
               (Thread/sleep or-gate-delay)
               (set-signal! output (logical-or (get-signal a1) (get-signal a2)))))]
    (add-watch a1 :or or-action)
    (add-watch a2 :or or-action)))

(defn half-adder [a b s c]
  (let [d (make-wire) e (make-wire)]
    (or-gate a b d)
    (and-gate a b c)
    (inverter c e)
    (and-gate d e s)))

(defn full-adder [a b c-in sum c-out]
  (let [s (make-wire) c1 (make-wire) c2 (make-wire)]
    (half-adder b c-in s c1)
    (half-adder a s sum c2)
    (or-gate c1 c2 c-out)))
