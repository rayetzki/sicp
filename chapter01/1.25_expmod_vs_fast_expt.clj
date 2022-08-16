(require '[lib.fermat :refer [expmod, fast-prime?]])
(require '[chapter01.1.16_exp_iterative :refer [fast-expt]])

(defn expmod-fastified [base exp m]
  (rem (fast-expt base exp) m))

(time (fast-prime? 5 101 expmod))
(time (fast-prime? 5 101 expmod-fastified))

;; Using expmod, on each step we modulate the result by m, so the resulting number never exceeds m
;; Using fast-expt, on each step we square the previous result, with modulation done only on the final step, which gives **long overflow** error when dealing with large ints
