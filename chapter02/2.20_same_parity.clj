(defn same-parity [& nums]
  (filter 
   (fn [a] 
     (if (true? (even? (first nums))) 
       (even? a)
       (odd? a)))
   nums))

(same-parity 1 2 3 4 5 6 7)
(same-parity 2 3 4 5 6 7)