(require '[lib.stream :refer [stream-map, stream-next]])

(defn sign-change-detector [a b] 
  (cond (and (<= a 0) (pos? b)) 1
        (and (>= a 0) (neg? b)) -1
        :else 0))

(defn zero-crossings [sense-data] 
  (stream-map sign-change-detector sense-data (stream-next sense-data)))