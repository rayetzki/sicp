(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (dec n)))))

;; parameter n bound to 6
;; body 
;; factorial is in global env, dec is global, if is global

;; Creates deep frames where each next frame is based on upper frames results
;; There must be base case provided, otherwise program will run out of frames --> STACK OVERFLOW

;; E1 -> (* n (factorial (- n 1)))
;; E2 -> (* n (* (- n 1) (factorial (dec (dec n)))))
;; E3 -> (* n (* (- n 1) (* (- (- n 1) 1) (factorial (dec (dec (dec n))))))))
;; E4 -> (* n (* (- n 1) (* (- (- n 1) 1) (* (- (- (- n 1) 1) 1) (factorial (dec (dec (dec (dec n))))))))))
;; E5 -> (* n (* (- n 1) (* (- (- n 1) 1) (* (- (- (- n 1) 1) 1) (* (- (- (- (- n 1) 1) 1) 1) 1) (factorial (dec (dec (dec (dec (dec n)))))))))))
;; E6 -> (* n (* (- n 1) (* (- (- n 1) 1) (* (- (- (- n 1) 1) 1) (* (- (- (- (- n 1) 1) 1) 1) 1) (* (* (- (- (- (- (- n 1) 1) 1) 1) 1) 1) (factorial (dec (dec (dec (dec (dec (dec n)))))))))))))
;; Reduce on each step in appropriate frame back to E1 until 720 is received
;; Return 720 - unbound

(defn fact-iter [n]
  (loop [product 1 counter n]
    (if (zero? counter)
      product
      (recur (* counter product) (dec counter)))))

;; E1 -> product 1, counter 6
;; E2 -> product 6, counter 5
;; E3 -> product 30, counter 4
;; E4 -> product 120, counter 3
;; E5 -> product 360, counter 2
;; E6 -> product 720, counter 1,
;; E7 -> return 720 from fact-iter - unbound