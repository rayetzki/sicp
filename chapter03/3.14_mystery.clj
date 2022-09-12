;; (defn mystery [x]
;;   (loop [x x y '()]
;;     (if (empty? x)
;;       y
;;       (let [temp (rest x)]
;;         (set-cdr! x y)
;;         (recur temp x)))))

;; (def v (mystery (list 'a 'b 'c 'd)))
;; (def w (mystery v))

;; 1: x = (list 'b 'c 'd); y = (list 'a)
;; 2: x = (list 'c 'd);    y = (list 'b 'a)
;; 3: x = (list 'd);       y = (list 'c 'b 'a)
;; 4: x = (list ());       y = (list 'd 'c 'b 'a)
;; v = (list ()); 
;; w = (list 'd 'c 'b 'a)
