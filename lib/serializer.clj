(ns lib.serializer)

(defn make-mutex []
  (let [status (atom false)]
    (fn [m]
      (cond (= m 'acquire) (compare-and-set! status true true)
            (= m 'release) (reset! status false)))))

(defn make-serializer []
  (let [mutex (make-mutex)]
    (fn [p]
      (fn [& args]
        (mutex 'acquire)
        (let [val (apply p args)]
          (mutex 'release)
          val)))))

