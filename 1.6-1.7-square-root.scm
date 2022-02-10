(define (sqrt x) 
  (define (square num) (* num num)) 
  (define (average a b) (/ (+ a b) 2))

  (define (good-enough-square? guess)
    (format #t "~a - old guess, ~a - new guess\n" guess (improve-square guess))
    (= (improve-square guess) guess))

  (define (improve-square guess)
    (average guess (/ x guess)))

  (define (sqrt-iter guess)
    (if (good-enough-square? guess)
      guess
      (sqrt-iter (improve-square guess))))
    
  (sqrt-iter 1.0)
)

