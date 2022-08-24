(require '[lib.math :refer [square]])

(defn square-list [items]
  (loop [left items transformed []]
    (if (empty? left)
      (reverse transformed)
      (recur (rest left) (cons (square (first left)) transformed)))))

(square-list (list 1 2))