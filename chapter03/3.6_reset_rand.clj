(defn new-rand [value]
  (let [x (atom value)]
    (fn [arg]
      (cond (= arg 'generate) (do 
                                (swap! x #(+ (rem % 3) (rand %)))
                                @x)
            (= arg 'reset) (reset! x value)
            :else (throw (Exception. "Operation not found"))))))