; f(n) = n if n < 3; 
; f(n) = f(n−1) + f(n−2) + f(n−3) if n >= 3;

; f(2) = 2;
; f(1) = 1;
; f(0) = 0;

(define (f2 n)
  (define (f-iter a b c n)
    (if (zero? n)
        a
        (f-iter b c (+ c (* 2 b) (* 3 a)) (- n 1))))
  (f-iter 0 1 2 n))
