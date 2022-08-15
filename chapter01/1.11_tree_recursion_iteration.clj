;; f(n) = n if n < 3
;; f(n) = f(n - 1) + 2 * f(n - 2) + 3 * f(n - 3)

(defn f-rec [n]
  (if (< n 3)
    n
    (+ (f-rec (- n 1)) (* 2 (f-rec (- n 2))) (* 3 (f-rec (- n 3))))))

(defn iter-f [a b c count]
  (if (zero? count)
    a
    (iter-f b c (+ c (* 2 b) (* 3 a)) (dec count))))

(defn f-iter [n]
  (iter-f 0 1 2 n))

;; (f-iter 5)
;; (iter-f 0 1 2 5)
;; (iter-f 1 2 (+ 2 (* 2 1) (* 3 0)) 4)
;; (iter-f 1 2 4 4)
;; (iter-f 2 4 (+ 4 (* 2 2) (* 3 1)) 3)
;; (iter-f 2 4 11 3)
;; (iter-f 4 11 (+ 11 (* 2 4) (* 3 2) 2))
;; (iter-f 9 9 25 2)
;; (iter-f 9 25 (+ 25 (* 2 9) (* 3 7) 1))
;; (iter-f 9 25 64 1)
;; (iter-f 25 64 (+ 68 (* 2 25) (* 3 9) 0))
;; (iter-f 25 64 145 0)
;; 25 <--

(f-rec 5)
