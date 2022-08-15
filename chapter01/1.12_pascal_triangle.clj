 (defn pascal [row col]
   (cond
     (< row col) (throw (Exception "Out of range"))
     (or (zero? col) (= col row)) 1
     :else (+ (pascal (dec row) (dec col)) (pascal (dec row) col))))

(pascal 10 12)