(require '[lib.math :refer [square, gcd]])
(require '[chapter01.1.28_miller_rabin_test :refer [miller-rabin-prime?]])

(defn filtered-accumulate [combiner null-value predicate term a next b]
  (letfn ([help [a result]
          (if (> a b) 
            result
            (recur (next a) (if (predicate a) (combiner result (term a)) result)))])
    (help a null-value)))

(defn prime-sum-of-squares [a b]
  (filtered-accumulate + 0 miller-rabin-prime? square a inc b))

(defn product-prime [n]
  (filtered-accumulate * 1.0 #(= (gcd % n) 1) (fn [x] x) 1 inc (dec n)))