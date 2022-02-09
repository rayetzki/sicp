(define (square x) (* x x)) 

(define (cube x)
  (cube-iter 1.1 x))

(define (cube-iter guess x)
  (if (good-enough-cube? guess x)
    guess
    (cube-iter (improve-cube guess x) x)))

(define (improve-cube guess x)
  (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(define (good-enough-cube? guess x) 
  (= (improve-cube guess x) guess))