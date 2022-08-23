(require '[chapter02.2.7_interval :refer [upper-bound, lower-bound, make-interval]])

(defn mul-interval-imp [x y]
  (letfn [(cross-zero? [a b] (and (<= a 0) (>= b 0)))]
    (let [a (lower-bound x)
          b (lower-bound y)
          c (upper-bound x)
          d (upper-bound y)]
      (cond (cross-zero? a b)
            (cond (cross-zero? c d)
                  (make-interval (min (* b c) (* a d)) (max (* a c) (* b d)))) 
            :else (let [factor (if (neg-int? c) c d)] 
                    (make-interval (* a factor) (* b factor))))
      (when (cross-zero? c d)
            (let [factor (if (neg-int? a) a b)]
              (make-interval (* factor c) (* factor d))))
      (if (neg-int? (* a c)) 
        (make-interval (* a d) (* b c)) 
        (make-interval (* a c) (* b d))))))

(mul-interval-imp (make-interval 2 3) (make-interval 3 4))