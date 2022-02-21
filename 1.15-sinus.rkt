; if x in rad. is relatively (<= 0.1) small -> sinx ~ x;
; else sinx = 3sin(x/3) - 4sin(x^3/3);

(define (cube x) (* x x x))
(define (p x) (- (* 3 x) (* 4 (cube x))))
(define (sine angle)
   (if (not (> (abs angle) 0.1))
       angle
       (p (sine (/ angle 3.0)))))

; If you empirically try increasing numbers, you will see, that the total call number is changing slower
; So O(n) = log(n);
; If it's not x / 3, but x, the complexity can move to linear