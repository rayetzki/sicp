(ns lib.deriv
  (:import java.lang.Math))

;; (dc / dx) = 0 if c is number or c is x
;; (dx / dx) = 1
;; (d (u + v) / dx) = (du / dx) + (dv / dx)
;; (d (uv) / dx) = (u * dv / dx) + (v * du / dx)

(defn variable? [x] (symbol? x))
(defn same-variable? [a b] 
  (and (variable? a) (variable? b)) (= a b))
(defn =number? [exp number] 
  (and (number? exp) (= exp number)))

(defn sum? [exp] (= (first exp) '+))
(defn addend [sum] (nth sum 1))
(defn augend [[_ __ & sum]] 
  (if (empty? (rest sum))
    (first sum)
    (cons '+ sum)))

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list '+ a1 a2)))

(defn product? [exp] (= (first exp) '*))
(defn multiplier [product] (nth product 1))
(defn multiplicand [[_ __ & product]] 
  (if (empty? (rest product))
    (first product)
    (cons '* product)))
(defn make-product [a1 a2]
  (cond (or (=number? a1 0) (=number? a2 0)) 0
        (=number? a1 1) a2
        (=number? a2 1) a1
        (and (number? a1) (number? a2)) (* a1 a2)
        :else (list '* a1 a2)))

(defn exponentiation? [exp] (= (first exp) '**))
(defn base [exp] (nth exp 1))
(defn exponent [exp] (nth exp 2))
(defn make-exp [base exp]
  (cond (=number? exp 0) 1
        (=number? base 1) 1
        (=number? exp 1) base
        (and (number? base) (number? exp)) (Math/pow base exp)
        :else (list '** base exp)))

(defn deriv [exp x] 
  (let [derive-sum (fn [] (make-sum
                           (deriv (addend exp) x)
                           (deriv (augend exp) x)))
        derive-product (fn []
                         (make-sum
                          (make-product (multiplier exp) (deriv (multiplicand exp) x))
                          (make-product (multiplicand exp) (deriv (multiplier exp) x))))
        derive-exponent (fn []
                          (let [b (base exp) n (exponent exp)]
                            (make-product
                             (make-product b (make-exp b (if (number? n) (dec n) '(- n 1))))
                             (deriv b x))))]
    (cond
      (number? exp) 0
      (variable? exp) (if (same-variable? exp x) 1 0)
      (sum? exp) (derive-sum)
      (product? exp) (derive-product)
      (exponentiation? exp) (derive-exponent)
      :else (throw (IllegalArgumentException. "Unknown type of expression -- DERIV" exp)))))