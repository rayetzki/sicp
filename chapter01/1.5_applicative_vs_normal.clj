(defn p [] p)

(defn test-appl-vs-normal [x y]
  (if (> x 0) 
    0 
    y))

(test-appl-vs-normal 0 (p))