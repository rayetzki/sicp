(defn last-pair [items]
  (if (and (list? items) (empty? (rest items)))
    items
    (last-pair (rest items))))