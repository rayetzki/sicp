(defn product-recursive [term a next b]
  (if (> a b)
    1
    (* (term a) (product-recursive term (next a) next b))))

(defn product-iterative [term a next b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (iter (next a) (* result (term a)))))]
    (iter a 1)))

(defn factorial [n]
  (if (<= n 1)
    1
    (product-iterative (fn [x] x) 1 inc n)))

(defn wallis-range [n]
  (letfn [(term [n] (*
                     (/ (* 2 n) (dec (* 2 n)))
                     (/ (* 2 n) (inc (* 2 n)))))]
    (product-iterative term 1.0 inc n)))

(defn pi4 []
  (double (/ (wallis-range 4) 2)))