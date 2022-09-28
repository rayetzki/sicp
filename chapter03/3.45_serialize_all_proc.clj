(def make-serializer identity)

(defn make-account-and-serializer [balance]
  (letfn [(withdraw [amount]
            (if (>= balance amount)
              (do (set! balance (- balance amount)) balance)
              "Insufficient funds"))
          (deposit [amount]
            (set! balance (+ balance amount)) balance)]
    (let [balance-serializer (make-serializer)]
      (fn [m]
        (cond (= m 'withdraw) (balance-serializer withdraw)
              (= m 'deposit) (balance-serializer deposit)
              (= m 'balance) balance
              (= m 'serializer) balance-serializer
              :else (throw (Error. "Unknown request: MAKE-ACCOUNT" m)))))))

(defn exchange [account1 account2]
  (let [difference (- (account1 'balance) (account2 'balance))]
     ((account1 'withdraw) difference)   ;; ->> acquire
     ((account2 'deposit) difference)))  ;; ->> acquire

(defn serialized-exchange [account1 account2]
  (let [serializer1 (account1 'serializer)   ;; ->> acquire
        serializer2 (account2 'serializer)]  ;; ->> acquire
    ((serializer1 (serializer2 exchange)) 
     account1 
     account2)))

;; If serialized automatically, that means that make-account-and-serializer already acquired rights to access account
;; So now it is not possible to create do exchanges between account