(ns lib.sum)

(defn sum-linear-rec [term a next b]
  (if (> a b)
    0
    (+ (term a) (sum-linear-rec term (next a) next b))))

(defn sum-iter [term a next b]
  (loop [a a result 0]
     (if (> a b)
       result 
       (recur (next a) (+ result (term a))))))
  