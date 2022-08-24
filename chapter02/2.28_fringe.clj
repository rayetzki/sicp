(ns chapter02.2.28_fringe)

(defn fringe [tree]
  (loop [node tree acc nil]
    (let [leave (first node)]
      (cond (empty? node) (reverse acc)
            (list? leave) (recur (concat leave (rest node)) acc)
            :else (recur (rest node) (cons leave acc))))))