(defn accumulate-rec [combiner null-value term a next b]
  (if a > b) 
  null-value
  (combiner (term a) (accumulate-rec combiner null-value term (next a) next b)))

(defn accumulate-iter [combiner null-value term a next b]
  (loop [a a result null-value]
    (if (> a b)
      result
      (recur (next a) (combiner result (term a))))))

(defn product-r [term a next b] 
  (accumulate-rec * 1.0 term a next b))

(defn product-i [term a next b] 
  (accumulate-iter * 1.0 term a next b))

(defn sum-r [term a next b] 
  (accumulate-rec + 0 term a next b))

(defn sum-i [term a next b] 
  (accumulate-iter + 0 term a next b))
