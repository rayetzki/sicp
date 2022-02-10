(define (cube x)
  (define (square num) (* num num))
  
  (define (good-enough-cube? guess) 
    (format #t "~a - old guess, ~a - new guess\n" guess (improve-cube guess))
    (= (improve-cube guess) guess))

  (define (improve-cube guess)
    (/ (+ (/ x (square guess)) (* 2 guess)) 3))

  (define (cube-iter guess)
    (if (good-enough-cube? guess)
      guess
      (cube-iter (improve-cube guess))))

  (cube-iter 1.0)
)
