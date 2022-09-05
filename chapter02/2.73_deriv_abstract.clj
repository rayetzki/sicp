(ns chapter02.2.73_deriv_abstract
  (:require [lib.deriv :refer [variable?, same-variable?, make-product, make-exp, make-sum]])
  (:refer-clojure :exclude [get]))
  
  #_{:clj-kondo/ignore [:inline-def]}
(defn install-package-deriv []
  (defmulti get #(keyword (str %1) (str %2)))

  (defn addend [sum] (first sum))
  (defn augend [sum] (second sum))
  (defn multiplier [product] (first product))
  (defn multiplicand [product] (second product))
  (defn base [exp] (first exp))
  (defn exponent [exp] (second exp))

  (defn operator [exp] (first exp))
  (defn operands [exp] (rest exp))

  (defn deriv [exp var]
    (cond (number? exp) 0
          (variable? exp) (if (same-variable? exp var) 1 0)
          :else ((get 'deriv (operator exp)) (operands exp) var)))

  (defmethod get :deriv/+ [_ __]
    (fn [exp var]
      (make-sum
       (deriv (addend exp) var)
       (deriv (augend exp) var))))

  (defmethod get :deriv/* [_ __]
    (fn [exp var]
      (make-sum
       (make-product (multiplier exp) (deriv (multiplicand exp) var))
       (make-product (multiplicand exp) (deriv (multiplier exp) var)))))

  (defmethod get :deriv/** [_ __]
    (fn [exp var]
      (let [b (base exp) n (exponent exp)]
        (make-product
         (make-product b (make-exp b (if (number? n) (dec n) '(- n 1))))
         (deriv b var))))))