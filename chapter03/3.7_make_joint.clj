(defn make-account [amount secret]
  (let [password (atom secret) total (atom amount)]
    (fn [key operation]
      (when (= operation 'new-secret) (not= @password key)) (reset! password key)
      (when (not= @password key) (throw (Exception. "Invalid password")))
      (fn [value]
        (cond (= operation 'withdraw) (swap! total - value)
              (= operation 'deposit) (swap! total + value)
              :else (throw (Exception. "Unknown operation type")))))))

(defn make-joint [acc old-password new-password]
  (try
    (acc old-password 'withdraw)
    (acc new-password 'new-secret)
    acc
    (catch Exception e e)))

(def peter-acc (make-account 100 'open-sesame))
((peter-acc 'open-sesame 'withdraw) 10)
(def paul-acc (make-joint peter-acc 'open-sesame 'rosebud))
((paul-acc 'rosebud 'withdraw) 10)