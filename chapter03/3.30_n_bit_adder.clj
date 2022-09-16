(require '[lib.digital :refer [full-adder, make-wire]])

(defn ripple-carry-adder [Ak Bk]
  (when-not (= (count Ak) (count Bk)) 
    (throw (Exception. "Signals count mismatch" (count Ak) (count Bk))))
  (let [c-in (make-wire) sum (make-wire) c-out (make-wire)]
    (loop [[A & rest-A] Ak [B & rest-B] Bk Sk (list @sum)]
      (if (nil? A)
        (cons c-out Sk)
        (do
          (full-adder c-in A B sum c-out)
          (recur (rest rest-A) (rest rest-B) (cons @sum Sk)))))))

