(defn accumulator [start]
  (let [current (atom start)]
   (fn [value] (swap! current + value))))

(def acc (accumulator 50))
(acc 5)