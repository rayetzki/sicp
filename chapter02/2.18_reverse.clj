(ns chapter02.2.18_reverse)

(defn reverse-arr [items]
  (loop [original items reversed '()]
    (if (empty? original)
      reversed
      (recur (rest original) (conj reversed (first original))))))