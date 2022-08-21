(ns lib.math)

(defn square [n] (* n n))
(defn average [x y] (/ (+ x y) 2))
(defn cube [x] (* x x x))
(defn halve [x] (/ x 2))
(defn divides? [a b] (zero? (rem b a)))
(defn gcd [a b]
  (if (zero? b)
    a
    (gcd b (rem a b))))

(defn deriv [g]
  (let [dx 0.00001]
    (fn [x] (/ (- (g (+ x dx)) (g x)) dx))))