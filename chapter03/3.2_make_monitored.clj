(defn make-monitored [f]
  (let [times (atom 0)]
    (fn [mf]
      (cond (= mf 'how-many-calls?) (deref times)
            (= mf 'reset-count) (reset! times 0)
            :else (do
                    (swap! times inc)
                    (f mf))))))

(def monitor (make-monitored inc))
(monitor 1)
(monitor 'how-many-calls?)
(monitor 'reset-count)
(monitor 'how-many-calls?)
(monitor 2)
(monitor 3)