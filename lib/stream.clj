(ns lib.stream)

(def empty-stream nil)
(defn stream-value [stream] (first stream))
(defn stream-next [stream] ((force (second stream))))
(defn empty-stream? [stream] (nil? stream))
(defn cons-stream [value next] (conj (if (vector? value) value (vector value)) (delay next)))

(defn stream-enumerate-interval [low high]
  (if (> low high)
    empty-stream
    (cons-stream low #(stream-enumerate-interval (inc low) high))))

(defn stream-filter [predicate stream]
  (cond (empty-stream? stream) empty-stream
        (predicate (stream-value stream)) (cons-stream (stream-value stream) #(stream-filter predicate (stream-next stream)))
        :else (stream-filter predicate (stream-next stream))))

(defn stream-ref [stream index]
  (cond (zero? index) (stream-value stream)
        (neg-int? index) stream
        (empty-stream? stream) nil
        :else (stream-ref (stream-next stream) (dec index))))

(defn stream-map [proc & argstreams]
  (if (empty-stream? (first argstreams))
    empty-stream
    (cons-stream
     (apply proc (map stream-value argstreams))
     #(apply stream-map
             (cons proc (map stream-next argstreams))))))

(defn stream-for-each [proc stream]
  (if (empty-stream? stream)
    'done
    (do
      (proc (stream-value stream))
      (stream-for-each proc (stream-next stream)))))

(defn scale-stream [stream factor]
  (stream-map #(* % factor) stream))

(defn display-stream [stream]
  (stream-for-each println stream))

(defn add-streams [s1 s2]
  (stream-map + s1 s2))
