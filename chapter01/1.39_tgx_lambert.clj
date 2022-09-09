(require '[lib.math :refer [square]])
(require '[chapter01.1.37_continued_fraction :refer [cont-frac]])

(defn tan-cf [x k] 
  (cont-frac (fn [i] (if (= i 1) x (* (square x) -1)))
             (fn [i] (dec (* 2.0 i)))
             k))