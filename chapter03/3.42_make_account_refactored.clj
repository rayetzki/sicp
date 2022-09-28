(def make-serializer identity)

(defn make-account [balance]
  (letfn [(withdraw [amount]
            (if (>= @balance amount)
              (do
                (swap! balance - amount)
                @balance)
              "Insufficient funds"))
          (deposit [amount]
            (swap! balance + amount)
            @balance)]
    (let [protected (make-serializer)
          protected-withdraw (protected withdraw)
          protected-deposit (protected deposit)]
      (fn [m]
        (cond (= m 'withdraw) protected-withdraw
              (= m 'deposit) protected-deposit
              (= m 'balance) balance)))))

;; Yes it's safe, 2 serialized procedures will never interleave