(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))

(defn cc [amount coin-values]
  (cond (zero? amount) 1 
        (or (neg-int? amount) (empty? coin-values)) 0 
        :else (+ 
               (cc amount (rest coin-values)) 
               (cc (- amount (first coin-values)) coin-values))))

(defn count-change [amount] 
  (cc amount us-coins))

(count-change 100)