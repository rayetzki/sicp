(defn my-map [f items]
  (reduce (fn [x accumulated] (cons (f x) accumulated)) nil items))

(defn append [seq1 seq2]
  (reduce cons seq1 seq2))

(defn length [sequence]
  (reduce (fn [acc _] (inc acc)) 0 sequence))