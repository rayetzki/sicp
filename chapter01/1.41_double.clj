(defn doubl [f]
  (fn [x]
    (f (f x))))

(((doubl (doubl doubl)) inc) 5)

