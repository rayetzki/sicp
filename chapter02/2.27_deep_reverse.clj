(require '[chapter02.2.18_reverse :refer [reverse-arr]])

(defn deep-reverse [items]
  (if (or (list? items) (vector? items))
    (map reverse-arr items)
    items))

(def x (list (list 1 2) (list 3 4)))

(reverse x)
(deep-reverse x)