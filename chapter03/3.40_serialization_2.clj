;; (def x 10)
;; (parallel-execute (serialize #(set! x (* x x)))
;;                   (serialize #(set! x (* x x x))))

;; 1000000 - if P1 then P2 or P2 then P1