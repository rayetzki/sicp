;; (defn make-cycle [x]
;;   (set-rest! (last-pair x) x)
;;   x)

;; (def z (make-cycle (list 'a 'b 'c))) --> (list 'a 'b 'c **pointer to (list 'a 'b 'c) **)
;; (last-pair z) --> infinite recursion

