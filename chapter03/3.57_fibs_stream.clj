(require '[lib.stream :refer [cons-stream, stream-ref, add-streams, stream-next]])

(defn fibgen [a b] (cons-stream a #(fibgen b (+ a b))))
(def fibs-gen (fibgen 0 1))

(def fibs 
  (cons-stream 0
               #(cons-stream 1
                            (fn [] (add-streams (stream-next fibs) fibs)))))

(time (stream-ref fibs 10)) ;; <-- at least 10 times longer then below
(time (stream-ref fibs-gen 10))

;; If stream-next wouldn't have been implemented using delay-force, it would require to recalculate each fib(n - 1) and calculate fib(n) 
;; on each step which is redundant