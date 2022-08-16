(require '[lib.fermat :refer [expmod]])

(defn miller-rabin-test [base exp]
  (cond 
    (zero? base) true 
    (= (expmod base (dec exp) exp) 1) (miller-rabin-test (dec base) exp)
    :else false))

(defn miller-rabin-prime? [n]
  (miller-rabin-test (dec n) n))