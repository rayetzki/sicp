;; (defn transfer [from-account to-account amount]
;;   ((from-account 'withdraw) amount))
;;   ((to-account 'deposit) amount)))

;; (defn serialized-exchange [account1 account2]
;;   (let [serializer1 (account1 'serializer)
;;        serializer2 (account2 'serializer)]
;;    ((serializer1 (serializer2 exchange)) account1 account2)))

;; Main difference is. that exchange requires reading current 2 account balances, and transfer already receives an amount.
;; No interleaving here.