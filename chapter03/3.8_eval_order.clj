 (defn make-f []
   (let [current (atom 0)]
     (fn [number]
       (let [existing @current]
         (reset! current number)
         existing))))

(def f (make-f))
(+ (f 0) (f 1))
(+ (f 1) (f 0))