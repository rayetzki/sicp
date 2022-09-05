(defn make-from-ang-mag [r a]
  (fn [op]
    (cond (= op 'magnitude) r
          (= op 'angle) a
          (= op 'real-part) (* (Math/cos a) r)
          (= op 'img-part) (* (Math/sin a) r)
          :else (throw (Exception. "Operation not found -- MAKE_FROM_ANG_MAGNITUDE" r a)))))
