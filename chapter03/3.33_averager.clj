(require '[lib.constraints :refer [forget-value!, set-value!, get-value, has-value?, error, connect]])
(require '[lib.math :refer [average]])

(defn averager [a b c]
  (letfn [(process-new-value []
            (cond (and (has-value? a) (has-value? b)) (set-value! c (average (get-value a) (get-value b)) me)
                  (and (has-value? b) (has-value? c)) (set-value! a (- (* 2 (get-value c)) (get-value b)) me)
                  (and (has-value? c) (has-value? a)) (set-value! b (- (* 2 (get-value c)) (get-value a)) me)))
          (process-forget-value []
            (forget-value! a me)
            (forget-value! b me)
            (forget-value! c me)
            (process-new-value))
          (me [request]
            (cond (= request :I-have-a-value) (process-new-value)
                  (= request :I-lost-my-value) (process-forget-value)
                  :else (error "Unknown request" request)))]
    (connect a me)
    (connect b me)
    (connect c me)
    me))