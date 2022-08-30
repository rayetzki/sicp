(defn generate-pairs [n]
  (mapcat (fn [i]
     (mapcat (fn [j]
        (map #(list i j %) (range 1 j)))
      (range 1 i)))
   (range 1 n)))

(defn trio-equals-sum? [trio sum]
  (= (+ (first trio) (second trio) (last trio)) sum))

(defn find-trio-with-sum [n sum]
  (filter #(trio-equals-sum? % sum) (generate-pairs n)))

(find-trio-with-sum 6 10)
