(require '[chapter01.1.37_continued_fraction :refer [cont-frac]])

(defn euler-seq [i] 
  (if (= (mod i 3) 2) 
    (* 2 (/ (+ i 1) 3)) 
    1))

(cont-frac (fn [i] 1.0) euler-seq 12)