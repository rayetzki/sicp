(require '[chapter02.2.53_memq :refer [memq]])

(defn pair? [data]
  (and (list? data) (= (count data) 2)))

(defn wrong-count-pairs [pairs]
  (if-not (pair? pairs)
    0
    (+ (wrong-count-pairs (first pairs)) (wrong-count-pairs (rest pairs)) 1)))

(defn count-pairs [pairs counted]
  (first
   (if (and (pair? pairs) (not (memq pairs counted)))
     (let [p-car (count-pairs (first pairs) (cons pairs counted))
           p-cdr (count-pairs (rest pairs) (rest p-car))]
       (cons (+ (first p-car) (first p-cdr) 1) (rest p-cdr)))
     (cons 0 counted))))

