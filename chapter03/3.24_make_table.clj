(defrecord Table [label categories])
(defrecord Category [name records])
(defrecord Record [key value])

(defn make-table [label]
  (let [table (Table. label (atom []))
        make-category #(swap! (:categories table) conj (Category. % (atom [])))
        make-record #(Record. %1 (atom %2))
        same-key? #(if (and (number? %1) (number? %2))
                     (<= (abs (- %1 %2)) 0.01)
                     (= %1 %2))
        get-category (fn [name]
                       (let [categories (filter #(same-key? name (:name %)) @(:categories table))]
                         (if (empty? categories)
                           (first (make-category name))
                           (first categories))))
        get-record (fn [category key]
                     (first (filter #(same-key? key (:key %)) @(:records category))))
        lookup (fn [name key]
                 (if-not (or (symbol? name) (symbol? key))
                   (throw IllegalArgumentException)
                   (let [category (get-category name)
                         record (get-record category key)]
                     @(:value record))))
        insert! (fn [name key value]
                  (let [category (get-category name)
                        record (get-record category key)]
                    (if record
                      (swap! (:value record) + value)
                      (swap! (:records category) conj (make-record key value)))))]
    (fn [operation]
      (cond (= operation 'lookup) lookup
            (= operation 'insert) insert!
            (= operation 'table) table
            :else (throw (IllegalArgumentException. "Operation not found"))))))

(def table (make-table '**sellings))
((table 'insert) 'Illia 'computers 1001)
((table 'insert) 'Andrew 8 1000)
((table 'lookup) 'Illia 'computers)
(table 'table)
