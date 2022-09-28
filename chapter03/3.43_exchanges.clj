;; first - 10$, second - 20$, third - 30$

;; After some amount of concurrent exchanges, resulting balance is the same in random order

;; (defn exchange [account1 account2]
;;   (let [difference (- (account1 'balance) (account2 'balance))]
;;      ((account1 'withdraw) difference)
;;      ((account2 'deposit) difference)))

;; Assume, first exchanges with second. Diff is 10$: first is 20$, second is 10$, third is still 30$. This order will remain in any exchange combination.
;; If exchanges are not serialized:
;; Exchange requires: 
;; 1. read account balances (2 times).
;; 2. run withdraw 
;; 3. calculate diff
;; 4. run deposit 
;; Interleaving can happen on any step