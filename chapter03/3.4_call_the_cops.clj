(defn call-the-cops [] "Cops are coming for you!!!")

(defn make-account [amount secret]
  (let [password secret total (atom amount) attempts (atom 0)]
    (fn [key operation]
      (cond
        (>= (deref attempts) 7) (fn [_] (call-the-cops))
        (not= password key) (fn [_]
                              (swap! attempts inc)
                              (str "Wrong password: " (str (- 7 @attempts)) " attempts left"))
        :else (fn [value]
                (when (and (= password key) (pos? (deref attempts))) (reset! attempts 0))
                (cond (= operation 'withdraw) (swap! total - value)
                      (= operation 'deposit) (swap! total + value)
                      :else "Unknown operation type"))))))

(def acc (make-account 100 'password))
((acc 'password 'withdraw) 20)
((acc 'pass1word 'deposit) 1)
((acc 'password 'deposit) 1)

