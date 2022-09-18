(require '[lib.digital :refer [full-adder, make-wire, get-signal]])

(defn ripple-carry-adder [Ak Bk]
  (when-not (= (count Ak) (count Bk))
    (throw (IllegalArgumentException. "Signals count mismatch" (count Ak) (count Bk))))
  (loop [[A & rest-A] (reverse Ak)
         [B & rest-B] (reverse Bk)
         Carry (make-wire)
         Sk (empty list)]
    (let [Sum (make-wire) Carry-Out (make-wire)]
      (if (nil? A)
        (str "Sum is: " Sk " " "Carry: " (get-signal Carry))
        (do
          (full-adder A B Carry Sum Carry-Out)
          (recur rest-A rest-B Carry-Out (cons (get-signal Sum) Sk)))))))