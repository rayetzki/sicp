(ns lib.fold)

(defn fold-left [op initial arr]
  (loop [result initial other arr]
    (if (empty? other)
      result
      (recur (op result (first other)) (rest other)))))

(defn fold-right [f init elems]
  (if (empty? elems)
    init
    (f (first elems) (fold-right f init (rest elems)))))

(defn acc-n [op init seqs]
  (when (seq (first seqs))
    (cons
     (fold-right op init (map first seqs))
     (acc-n op init (map rest seqs)))))