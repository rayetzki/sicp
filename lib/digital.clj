(ns lib.digital)

(defn make-wire
  ([] (agent 0))
  ([default] (agent default :validator #(or (= default 0) (= default 1)))))

(defn get-signal [signal] (deref signal))
(defn set-signal! [signal value] (send signal + value))
(def inverter-delay 2)
(def and-gate-delay 3)
(def or-gate-delay 5)

(defn probe [name wire]
  (add-watch wire (keyword name)
             (fn [_ __ ___ ____]
               (newline)
               (println name)
               (println " ")
               (println (new java.util.Date))
               (println " New value = ")
               (println (get-signal wire)))))

(defn logical-not [signal]
  (cond (= signal 0) 1
        (= signal 1) 0
        :else (throw (IllegalArgumentException. "Signal is incorrect" (get-signal signal)))))

(defn logical-and [s1 s2]
  (cond (and (= s1 1) (= s2 1)) 1
        (and (<= s1 1) (<= s2 1)) 0
        :else (throw (IllegalArgumentException. "Signal is incorrect" (get-signal s1) (get-signal s2)))))

(defn logical-or [s1 s2]
  (cond (and (= s1 0) (= s2 0)) 0
        (and (<= s1 1) (<= s2 1)) 1
        :else (throw (IllegalArgumentException. "Signal is incorrect" (get-signal s1) (get-signal s2)))))

(defn inverter [input output]
  (letfn [(go []
              (Thread/sleep inverter-delay)
              (set-signal! output (logical-not (get-signal input))))]
    (go)
    (add-watch input :invert go)))

(defn and-gate [a1 a2 output]
  (letfn [(go []
              (Thread/sleep and-gate-delay)
              (set-signal! output (logical-and (get-signal a1) (get-signal a2))))]
    (go)
    (add-watch a1 :and go)
    (add-watch a2 :and go)))

(defn or-gate [a1 a2 output]
  (letfn [(go []
              (Thread/sleep or-gate-delay)
              (set-signal! output (logical-or (get-signal a1) (get-signal a2))))]
    (go)
    (add-watch a1 :or go)
    (add-watch a2 :or go)))

(defn half-adder [a b sum carry]
  (let [d (make-wire) e (make-wire)]
    (or-gate a b d)
    (and-gate a b carry)
    (inverter carry e)
    (and-gate d e sum)))

(defn full-adder [a b c-in sum c-out]
  (let [s (make-wire) c' (make-wire) c'' (make-wire)]
    (half-adder b c-in s c')
    (half-adder a s sum c'')
    (or-gate c' c'' c-out)))

(def input-1 (make-wire 1))
(def input-2 (make-wire 1))
(def sum (make-wire))
(def carry (make-wire))

;; (probe 'sum sum)
;; (probe 'carry carry)
;; (full-adder input-1 input-2 carry sum carry)
