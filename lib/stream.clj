(ns lib.stream)

(defrecord Stream [value next])

(def empty-stream (Stream. nil nil))
(defn stream-value [stream] (:value stream))
(defn stream-next [stream] ((force (:next stream))))
(defn empty-stream? [stream] (nil? (stream-value stream)))
(defn cons-stream [value next] (Stream. value (delay next)))

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
    (cons-stream (apply proc (map stream-value argstreams))
                 #(apply stream-map (cons proc (map stream-next argstreams))))))

(defn stream-for-each [proc stream]
  (if (empty-stream? stream)
    'done
    (do
      (proc (stream-value stream))
      (stream-for-each proc (stream-next stream)))))

(defn stream-scale [stream factor]
  (stream-map #(* % factor) stream))

(defn display-stream [stream]
  (stream-for-each println stream))

(defn stream->list [stream n]
  (if (zero? n)
    (empty list)
    (cons (stream-value stream) (stream->list (stream-next stream) (dec n)))))

(defn add-streams [s1 s2] 
  (stream-map + s1 s2))

(defn multiply-streams [s1 s2] 
  (stream-map * s1 s2))

(defn divise-streams [s1 s2]
  (stream-map / s1 s2))

(defn integers-from-n [n]
  (cons-stream n #(integers-from-n (inc n))))

(defn stream-merge [s1 s2]
  (cond (empty-stream? s1) s2
        (empty-stream? s2) s1
        :else (let [s1-val (stream-value s1) s2-val (stream-value s2)]
                (cond (< s1-val s2-val) (cons-stream s1-val #(stream-merge (stream-next s1) s2))
                      (> s1-val s2-val) (cons-stream s2-val #(stream-merge s1 (stream-next s2)))
                      :else (cons-stream s1-val #(stream-merge (stream-next s1) (stream-next s2)))))))

(defn partial-sums [stream]
  (cons-stream (stream-value stream)
               #(add-streams (stream-next stream) (partial-sums stream))))

(defn stream-interleave [s1 s2]
  (if (empty-stream? s1)
    s2
    (cons-stream (stream-value s1) #(stream-interleave s2 (stream-next s1)))))