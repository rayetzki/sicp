(require '[lib.digital :refer [set-signal!, or-gate-delay, logical-or, get-signal]])

(defn or-gate [a1 a2 output]
  (letfn [(or-action []
            @(future
               (Thread/sleep or-gate-delay)
               (set-signal! output (logical-or (get-signal a1) (get-signal a2)))))]
    (add-watch a1 :or or-action)
    (add-watch a2 :or or-action)))
