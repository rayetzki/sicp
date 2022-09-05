(require '[lib.fold :refer [fold-right]])

(defn horner-eval [x coefficient] 
  (fold-right #((+ %1 (* x %2))) 0 coefficient))

(horner-eval 2 (list 1 3 0 5 0 1))