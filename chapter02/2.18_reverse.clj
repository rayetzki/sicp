(defn reversed-arr [items]
  (loop [original items reversed []]
    (if (= (count items) (empty? original))
      reversed
      (recur (rest original) (conj reversed (last items))))))