(require '[lib.deriv :refer [variable?, same-variable?, addend, sum?, make-sum, =number?, augend, multiplicand, multiplier, product?, make-product]])

(defn make-exp [base exp]
  (cond (=number? exp 0) 1
        (=number? base 1) 1
        (=number? exp 1) base
        (and (number? base) (number? exp)) (Math/pow base exp)
        :else (list '** base exp)))
(defn exponentiation? [exp] (= (first exp) '**))
(defn base [exp] (nth exp 1))
(defn exponent [exp] (nth exp 2))

(defn deriv [exp x]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-variable? exp x) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) x)
                         (deriv (augend exp) x))
    (product? exp) (make-sum
                    (make-product (multiplier exp) (deriv (multiplicand exp) x))
                    (make-product (multiplicand exp) (deriv (multiplier exp) x)))
    (exponentiation? exp) (let [b (base exp) n (exponent exp)]
                            (make-product
                             (make-product b (make-exp b (if (number? n) (dec n) '(- n 1))))
                             (deriv b x)))
    :else (throw (IllegalArgumentException. "Unknown type of expression -- DERIV" exp))))