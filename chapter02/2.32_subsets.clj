(defn subsets [s]
  (if (empty? s) 
    (list s)
    (let [tail (subsets (rest s))]
      (concat tail (map #(cons (first s) %) tail)))))

(subsets (list 1 2 3))