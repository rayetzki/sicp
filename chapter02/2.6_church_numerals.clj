(defn zero [f] identity)

(defn add-1 [n]
  (fn [f]
    (fn [x]
      (f ((n f) x)))))

(def one (add-1 zero))
(def two (add-1 one))

(defn add [a b] 
  (fn [f] 
    (fn [x] 
      ((a f) ((b f) x)))))

(((add two one) dec) 0)

(take-last (range 1 20))