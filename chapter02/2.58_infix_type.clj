(require '[lib.deriv :refer [=number?]])

(defn make-product [a1 a2]
  (cond (or (=number? a1 0) (=number? a2 0)) 0
        (=number? a1 1) a2
        (=number? a2 1) a1
        (and (number? a1) (number? a2)) (* a1 a2)
        :else (list a1 '* a2)))

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list a1 '+ a2)))

(defn make-exponentiation [base exponent]
  (cond (zero? exponent) 1
        (= 1 exponent) base
        :else (list base '** exponent)))

(defn sum? [exp] (= (second exp) '+))
(defn product? [exp] (= (second exp) '*))
(defn multiplier [product] (first product))
(defn exponentiation? [exp] (= (second exp) '**))
(defn base [exp] (first exp))
(defn exponent [exp] (last exp))