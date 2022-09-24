(require '[lib.constraints :refer [has-value?, error, connect, get-value, set-value!, forget-value!, make-connector, probe]])

(defn squarer [a b]
  (letfn [(process-new-value []
            (cond
              (has-value? a) (set-value! b (Math/pow (get-value a) 2) me)
              (has-value? b) (if (neg-int? (get-value b))
                               (error "Square is less than 0 -- SQUARER" (get-value b))
                               (set-value! a (int (Math/sqrt (get-value b))) me))))
          (process-forget-value []
            (forget-value! a me)
            (forget-value! b me)
            (process-new-value))
          (me [request]
            (cond (= request :I-have-a-value) (process-new-value)
                  (= request :I-lost-my-value) (process-forget-value)
                  :else (error "Unknown request" request)))]
    (connect a me)
    (connect b me)
    me))

(def a (make-connector))
(def b (make-connector))
(probe "Sqrt: " a)
(probe "Square: " b)
(squarer a b)
(set-value! b 9 'user)