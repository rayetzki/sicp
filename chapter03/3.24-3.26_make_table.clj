(defrecord Table [label records])
(defrecord Record [key value])
(defrecord Tree [entry left right])

(defn- make-tree [entry left right]
  (Tree. (atom entry) left right))

(defn list->tree [elems]
  (letfn [(partial-tree [elements quantity]
            (if (zero? quantity)
              (cons nil elements)
              (let [left-size (quot (dec quantity) 2)
                    [left-tree & right-elements] (partial-tree elements left-size)
                    right-size (- quantity (inc left-size))
                    this-entry (first right-elements)
                    [right-tree & remaining] (partial-tree (rest right-elements) right-size)]
                (cons (make-tree this-entry left-tree right-tree) remaining))))]
    (first (partial-tree elems (count elems)))))

(defn tree->list [tree]
  (if (empty? tree)
    nil
    (letfn [(help [subtree result]
              (if (nil? subtree)
                result
                (help (:left subtree) (cons (:entry subtree)
                                            (help (:right subtree) result)))))]
      (help tree []))))

(defn adjoin-set [set record]
  (loop [subset set result []]
    (cond (empty? subset) (reverse (conj set record))
          (pos? (compare @(:entry record) @(:entry (first subset)))) set
          (neg? (compare @(:entry record) @(:entry (first subset)))) (concat result [record] subset)
          :else (recur (rest subset) (concat result [(first subset)])))))

(defn make-table [label]
  (let [table (Table. label (atom []))
        make-record (fn [key value] (Record. key (atom value)))
        find-rec (fn [key tree]
                   (let [head (:entry tree)]
                     (cond (empty? head) false
                           (pos? (compare key (:key head))) head
                           (neg? (compare key (:key head))) (recur key (:left tree))
                           :else (recur key (:right tree)))))
        lookup (fn [& keys]
                 (loop [[first-key & rest-keys] keys found nil]
                   (cond (or (not (list? @(:entry table))) (seq? @(:entry @(:records table)))) false
                         (nil? first-key) (:value found)
                         :else (let [next (find-rec first-key @(:records table))]
                                 (if next (recur rest-keys next) false)))))
        insert! (fn [value & keys]
                  (loop [[key & rest-keys] keys]
                    (let [next (find-rec key @(:records table))]
                      (if next
                        (if (empty? rest-keys)
                          (reset! next (make-record key value))
                          (recur rest-keys))
                        (let [new-record (make-record key value)
                              new-subtable-list (adjoin-set new-record (tree->list @(:records table)))
                              new-subtable-tree (list->tree new-subtable-list)]
                          (if (empty? rest-keys)
                            (reset! (:records table) new-subtable-tree)
                            (do
                              (reset! (:value next) (:value new-record))
                              (reset! (:records table) new-subtable-tree)
                              (recur rest-keys)))))))
                  (when-not (list? keys)
                    (throw (IllegalArgumentException. "keys is not a list -- INSERT!" keys))))]
    (fn [operation]
      (cond (= operation 'lookup) lookup
            (= operation 'insert) insert!
            (= operation 'table) table
            :else (throw (IllegalArgumentException. "Operation not found"))))))

(def table (make-table '**sellings))
((table 'insert) 1001 'computers)
((table 'insert) 1001 'phones)
((table 'insert) 10 'peaches)
((table 'lookup) 'computers)
(table 'table)