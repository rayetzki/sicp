(defn make-account [amount secret]
  (let [password secret total (atom amount)]
    (fn [key operation]
      (if (not= password key)
        (throw (Exception. "Invalid password"))
        (fn [value]
          (cond (= operation 'withdraw) (swap! total - value)
                (= operation 'deposit) (swap! total + value)
                :else (throw (Exception. "Unknown operation type"))))))))

(def acc (make-account 100 'secret-password))
((acc 'secret-password 'withdraw) 40)
((acc 'some-other-password 'deposit) 50)