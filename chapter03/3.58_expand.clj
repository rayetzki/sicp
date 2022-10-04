(require '[lib.stream :refer [cons-stream, stream-ref]])

(defn expand [num den radix]
  (cons-stream (quot (* num radix) den)
               #(expand (rem (* num radix) den) den radix)))

(stream-ref (expand 1 7 10) 4)
(expand 3 8 10) ;; 3 7 5 0 0....
(expand 1 7 10) ;; 1 4 2 8 5 7 .. 1 4 2 8 5 7 ..
;; floating point representation