(defn for-each [tasks f]
  (loop [left tasks]
    (when-not (empty? left)
      (f (first left))
      (recur (rest left)))))

(for-each [1 2] (fn [x] (println (inc x))))