(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(defn mem [f]
  (let [memo (atom (hash-map))]
    (fn [& args]
      (let [cached (get @memo args)]
        (if cached
          cached
          (do
            (swap! memo assoc args (apply f args))
            (get @memo args)))))))