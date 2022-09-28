;; (def balance 100)
;; (set! balance (+ balance 10))
;; (set! balance (- balance 20))
;; (set! balance (- balance (/ balance 2)))

;; if run sequentially, balance can have such final values:
;; 1. 45 - (1, 2, 3) or (2, 1, 3)
;; 2. 40 - (3, 1, 2) or (3, 2, 1)
;; 3. 50 - (2, 3, 1)
;; 4. 35 - (1, 3, 2)

;; if run in parallel: 
;; 1. 110
;; 2. 80
;; 3. 90
;; 4. 60
;; 5. 55
;; 6. 50
;; 7. 40
;; 8. 30