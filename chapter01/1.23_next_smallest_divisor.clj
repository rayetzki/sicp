(require '[lib.math :as math])

(defn next-divisor [n]
  (if (even? n)
    (inc n)
    (inc (inc n))))

(defn find-divisor [n test-divisor]
  (cond (> (math/square test-divisor) n) n
        (math/divides? test-divisor n) test-divisor
        :else (find-divisor n (next test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= (smallest-divisor n) n))