(defn augend [[_ __ & sum]]
  (if (empty? (rest sum))
    (first sum)
    (cons '+ sum)))

(defn multiplicand [[_ __ & product]]
  (if (empty? (rest product))
    (first product)
    (cons '* product)))