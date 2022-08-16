(require '[lib.math :refer [halve]])

(defn expmod [base exp m]
  (cond (zero? exp) 1
        (even? exp) (rem (* (expmod base (halve exp) m) (expmod base (halve exp) m)) m)
        :else (rem (*' base (expmod base (dec' exp) m)) m)))

;; Same calculations made twice on each step, which kills all O(log(n)) benefits when using big numbers