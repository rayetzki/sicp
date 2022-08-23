(defn reversed-arr [items]
  (loop [original items reversed []]
    (if (= (count items) (count reversed))
      reversed
      (recur (rest original) (conj reversed (last items))))))