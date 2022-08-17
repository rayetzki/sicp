(ns lib.sum)

(defn sum-linear-rec [term a next b]
  (if (> a b)
    0
    (+ (term a) (sum-linear-rec term (next a) next b))))

(defn sum-iter [term a next b]
  (letfn [(iter [a result] 
                (if (> a b)
                  result
                  (iter (next a) (+ result (term a)))))]
    (iter a 0)))