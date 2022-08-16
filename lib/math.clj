(ns lib.math)

(defn square [n] (* n n))
(defn average [x y] (/ (+ x y) 2))
(defn cube [x] (* x x x))
(defn halve [x] (/ x 2))
(defn divides? [a b] (zero? (rem b a)))