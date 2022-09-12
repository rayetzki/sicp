(ns chapter02.2.53_memq)

(defn memq [item symbols-list]
  (let [symb (first symbols-list)]
    (cond
      (empty? symbols-list) false
      (= item (if (list? symb) (memq item symb) symb)) true
      :else (memq item (rest symbols-list)))))