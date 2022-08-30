(require '[lib.fold :refer [fold-left]])

(defn my-map [f items]
  (fold-left #(concat %1 (list (f %2))) nil items))

(defn append [seq1 seq2]
  (fold-left #(concat %1 (list %2)) seq1 seq2))

(defn length [sequence]
  (fold-left (fn [acc _] (inc acc)) 0 sequence))