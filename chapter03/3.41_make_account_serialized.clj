(require '[lib.serializer :refer [make-serializer]])

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
    (let [protected (make-serializer)]
      (fn [m]
        (cond (= m 'withdraw) (protected withdraw)
              (= m 'deposit) (protected deposit)
              (= m 'balance) (protected (fn [] balance)))))))

;; withdraw, deposit do series of actions, balance reads and returns a value -> no interleaving possible.