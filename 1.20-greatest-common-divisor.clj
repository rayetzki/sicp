(defn gcd [a b]
  (if (zero? b)
    a
    (gcd b (mod a b))))

;; (gcd 206 40) - via applicative process
;; (gcd 40 (mod 206 40)) 
;; (gcd 40 6)
;; (gcd 6 (mod 40 6))
;; (gcd 6 4)
;; (gcd 4 (mod 6 4))
;; (gcd 4 2)
;; (gcd 2 (mod 4 2))
;; (gcd 2 0) ->> 2; mod operations calc. 4 times

;; (gcd 206 40) - via normal-order process
;; (gcd 40 (mod 206 40))
;; (if (zero? (mod 206 40))) --> 6
;; (gcd (mod 206 40) (mod 40 (mod 206 40)))
;; (if (zero? (mod 40 (mod 206 40)))) --> 4
;; (gcd (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))))
;; (if (zero? (mod (mod 206 40) (mod 40 (mod 206 40))))) --> 2
;; (gcd (mod (mod 206 40) (mod 40 (mod 206 40)))) (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))))))))
;; (if zero? (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40))))))))) --> 0
;; (mod (mod 206 40) (mod 40 (mod 206 40))))

;; (gcd 2 0); mod operations calc 18 times = 14 eval time + 4 reduction time

