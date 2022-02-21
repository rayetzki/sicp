;             1
;          1     1 
;       1     2     1
;    1     3     3     1
; 1     4     6     4     1

; always = 1 on edges
; within = sum of above
; write procedure

(define (pascal row cell)
  (cond ((= cell 1) (= row cell) 1)
        ((> cell row) 0)
        ((< cell 0) 0)
        ((+ (pascal (- row 1) (- cell 1)) (pascal (- row 1) cell)))))
