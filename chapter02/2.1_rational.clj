(ns chapter02.2.1_rational
  (:require [lib.math :refer [gcd]]))

(defn make-rat 
  ([n] (make-rat n 1))
  ([n d]
   (let [g (gcd n d)
         n (/ n g)
         d (/ d g)]
     [(if (neg? d) (* -1 n) n) (abs d)])))

(defn numer [rat] (first rat))
(defn denom [rat] (last rat))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y)) (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y)) (* (numer y) (denom x))) 
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y)) (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y)) (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y)) (* (denom x) (numer y))))

(defn print-rat [x]
  (print (numer x))
  (print "/")
  (print (denom x)))

(print-rat (make-rat 10 20))
(print-rat (make-rat 10 -15))