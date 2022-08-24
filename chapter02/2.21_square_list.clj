(require '[lib.math :refer [square]])

(defn square-list [items]
  (if (empty? items)
    nil
    (cons (square (first items)) (square-list (rest items)))))

(defn square-list [items]
  (map square items))