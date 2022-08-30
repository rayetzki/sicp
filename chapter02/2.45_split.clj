
(def beside identity)
(def below identity)

(defn split [f g] 
  (fn [p n]
    (letfn [(help [painter times]
              (if (zero? n)
                p
                (let [smaller (help painter (dec times))]
                  (f painter (g smaller smaller)))))]
      (help p n))))


(def right-split (split beside below))
(def up-split (split below beside))