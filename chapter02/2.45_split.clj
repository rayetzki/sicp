
(def beside identity)
(def below identity)

(defn split [relative-to-painter between-each-other]
  (fn [painter n]
    (if (zero? n)
      painter
      (let [smaller (recur painter (dec n))]
        (relative-to-painter painter (between-each-other smaller smaller))))))

(def right-split (split beside below))
(def up-split (split below beside))