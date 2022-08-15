(defn Akkerman [x y]
  (cond (zero? y) 0
        (zero? x) (* 2 y)
        (= y 1) 2
        :else (Akkerman (dec x) (Akkerman x (dec y)))))

;; A(1 10)
;; A(0 A(1 9))
;; A(0 A(0 A(1 8)))
;; A(0 A(0 A(0 A(1 7))))
;; A(0 A(0 A(0 A(0 A(1 6)))))
;; A(0 A(0 A(0 A(0 A(0 A(1 5)))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(1 4)))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(1 3))))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(1 2)))))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 1))))))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 2)))))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(0 A(0 4))))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 A(0 8)))))))
;; A(0 A(0 A(0 A(0 A(0 A(0 16))))))
;; A(0 A(0 A(0 A(0 A(0 32)))))
;; A(0 A(0 A(0 A(0 64))))
;; A(0 A(0 A(0 128)))
;; A(0 A(0 256))
;; A(0 512)
;; 1024

;; A(2 4)
;; A(1 16)
;; 2 ^ 16 = 65536

;; A(3 3)
;; A(2 4)
;; A(1 16)
;; 2 ^ 16 = 65536

(defn f [n] (Akkerman 0 n))
(defn g [n] (Akkerman 1 n))
(defn h [n] (Akkerman 2 n))
(defn k [n] (* 5 n n))

;; f -> 2 * n
;; g -> 2 ** n
;; h -> 2 ** (n * 2)
;; k -> 5 * (n ** 2)


