(defn memq [item symbols-list]
  (let [symb (first symbols-list)]
    (cond
      (empty? symbols-list) false
      (= item (if (list? symb) (memq item symb) symb)) true
      :else (memq item (rest symbols-list)))))

(list 'a 'b 'c)
(list (list 'george))
(rest '((x1 x2) (y1 y2)))
(list? (first '((x1 x2) (y1 y2))))
(memq 'red '((red shoes) (blue socks)))
(memq 'red '(red shoes blue socks))