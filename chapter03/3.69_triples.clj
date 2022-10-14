(require '[lib.stream :refer [cons-stream, add-streams, stream-value, stream-next, stream-map, stream-interleave, stream-filter]])
(require '[chapter03.3.66_pairs_interleave :refer [pairs]])
(require '[lib.math :refer [square]])

(def ones (cons-stream 1 (fn [] ones)))
(def integers (cons-stream 1 #(add-streams ones integers)))

(defn triples [S T U]
  (cons-stream (list (stream-value S) (stream-value T) (stream-value U))
               #(stream-interleave 
                 (stream-map (fn [p] (list (stream-value S) (first p) (second p))) (pairs (stream-next T) (stream-next S)))
                 (triples (stream-next S) (stream-next T) (stream-next U)))))

(defn pithagorean-triples [S T U]
  (let [t (triples S T U)]
    (stream-filter #(= (square (nth % 2)) (+ (square (first %)) (square (second %)))) t)))

(def p (pithagorean-triples integers integers integers))