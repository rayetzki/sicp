(require '[lib.stream :refer [cons-stream, multiply-streams, integers-from-n]])

(def factorials 
  (cons-stream 1 #(multiply-streams (integers-from-n 2) factorials)))

;; 1 2 3 4 5 6 7 8 9
;; 1