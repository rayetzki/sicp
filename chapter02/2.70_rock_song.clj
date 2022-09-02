(require '[lib.haffman :refer [decode]])
(require '[chapter02.2.68_encode_haffman :refer [encode]])
(require '[chapter02.2.69_generate_huffman_tree :refer [generate-huffman-tree]])

(def song-map '((A 2) (BOOM 1) (GET 2) (JOB 2) (NA 16) (SHA 3) (YIP 9) (WAH 1)))
(def song '(GET A JOB SHA NA NA NA NA NA NA NA GET A JOB SHA NA NA NA NA NA NA NA NA NA WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP SHA BOOM))
(def tree (generate-huffman-tree song-map))

(def message (encode tree song))
(* 3 (count song)) ;; <-- 108 fixed bits length
(decode message tree)
(count message) ;; <-- 84 bits

;; Let's say we have 