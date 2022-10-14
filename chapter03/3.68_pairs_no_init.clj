(require '[lib.stream :refer [cons-stream, add-streams, stream-value, stream-next, stream-map, stream-interleave]])

(def ones (cons-stream 1 (fn [] ones)))
(def integers (cons-stream 1 #(add-streams ones integers)))

(defn pairs [s t]
  (stream-interleave (stream-map (fn [x] (list (stream-value s) x)) t)
                     (pairs (stream-next s) (stream-next t))))

(def p (pairs integers integers)) ;; --> recursively calls itself, no delay