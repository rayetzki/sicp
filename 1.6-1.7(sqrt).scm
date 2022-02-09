(define (square x) (* x x)) 
(define (average x y) (/ (+ x y) 2))

(define (good-enough-square? guess x)
  (format #t "~a - old guess, ~a - new guess\n" guess (improve-square guess x))
  (= (improve-square guess x) guess))

(define (sqrt x) 
  (sqrt-iter 1.0 x))

(define (improve-square guess x)
  (average guess (/ x guess)))

(define (sqrt-iter guess x)
  (if (good-enough-square? guess x)
    guess
    (sqrt-iter (improve-square guess x) x)))