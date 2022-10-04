(require '[lib.stream :refer [cons-stream, add-streams]])

(def s (cons-stream 1 #(add-streams s s)))
;; 1 2 4 8 .... 2 ** n