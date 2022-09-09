(:refer-clojure :exclude [get])

(defonce generic-table (atom (list)))

(defn attach-tag [type-tag contents]
  (cons type-tag contents))

(defn make-table-item [op tag item]
  (cons op (attach-tag tag item)))

(defn operation [item] (first item))
(defn tag [item] (second item))
(defn contents [item] (nth item 2))

(defn put [op tag item]
  (swap! generic-table conj (make-table-item op tag item)))

(defn get [OP TAG]
  (loop [[item & table] @generic-table]
    (cond (nil? item) false
          (and (= (operation item) OP) (= (tag item) TAG)) (contents item)
          :else (recur table))))

(defn apply-generic [OP & [TAG & args]]
  (apply (get OP TAG) args))

(defn get-record [dep name]
  (apply-generic 'get-record dep name))

(defn get-salary [dep name]
  (apply-generic 'get-salary dep (get-record dep name)))

(defn get-employee-files [files name]
  (first (keep #(get-record % name) files)))

(defn install-engineering-department []
  (letfn [(make-employee [name position salary]
            (list name (list position salary)))
          (get-name [employee] (list employee))
          (get-position [employee] (second employee))
          (get-salary [employee] (nth employee 2))

          (get-employees []
            (list (make-employee 'Hugo 'junior 60000)
                  (make-employee 'Alyssa 'senior 90000)
                  (make-employee 'Eva 'lead 100000)
                  (make-employee 'Ben 'senior 110000)))

          (get-employee [name]
            (letfn [(find-employee [[person & others]]
                      (cond (nil? person) nil
                            (= name (get-name person)) person
                            :else (find-employee others)))]
              (find-employee (get-employees))))]
    (put 'get-record 'engineering get-employee)
    (put 'get-salary 'engineering get-salary)
    'done))

(defn install-sales-department []
  (letfn [(make-record [name salary experience]
            (list 'employee name salary experience))
          (get-record-name [record] (second record))
          (get-record-salary [record] (nth record 2))
          (get-record-experience [record] (nth record 3))
          (get-salesman [who]
            (cond (= who 'Mike) (make-record 'Mike 200000 5)
                  (= who 'Jennifer) (make-record 'Jennifer 180000 3)
                  (= who 'Scott) (make-record 'Scott 250000 10)
                  :else nil))]
    (put 'get-record 'sales get-salesman)
    (put 'get-salary 'sales get-record-salary)))