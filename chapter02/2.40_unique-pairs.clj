(require '[lib.fermat :refer [prime?]])

(defn make-pair-sum [pair]
  (list (first pair) (last pair) (+ (first pair) (last pair))))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (last pair))))

(defn unique-pairs [n]
  (mapcat (fn [i] (map #(list i %) (range 1 (dec i))))
          (range 1 n)))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum?
               (unique-pairs n))))

(prime-sum-pairs 5)

