;; (def x 10)
;; (def serializer (make-serializer))
;; (parallel-execute
;;  (fn [] (set! x ((serializer #(* x x)))))
;;  (serializer #(set! x (inc x))))

;; 101: P1 sets x to 100 and then P2 increments x to 101.
;; 121: P2 increments x to 11 and then P1 sets x to x * x.
;; 11:  P2 accesses x, then P1 sets x to 100, then P2 sets x.
;; 100: P1 accesses x (twice), then P2 sets x to 11, then P1 sets x