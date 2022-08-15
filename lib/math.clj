(ns lib.math)

(defn square [n] (* n n))
(defn average [x y] (/ (+ x y) 2))
(defn cube [x] (* x x x))
(defn divides? [a b] (zero? (rem b a)))