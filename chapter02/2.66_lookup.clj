(defn lookup [given-key set-of-records]
  (cond (empty? set-of-records) false
        (= given-key (first (keys (first set-of-records)))) true
        :else (lookup given-key (rest set-of-records))))

(defn lookup-tree [given-key set-of-records]
  (loop [[{id :id :as entry} left-branch right-branch :as tree] set-of-records]
    (cond (empty? tree) nil
          (= given-key id) entry
          (< given-key id) (recur left-branch)
          (> given-key id) (recur right-branch))))