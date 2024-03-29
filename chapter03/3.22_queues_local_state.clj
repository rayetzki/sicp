(defrecord Queue [front rear])

(defn make-queue []
  (let [queue (Queue. (atom '()) (atom '()))
        front-ptr (:front queue)
        rear-ptr (:rear queue)
        empty-queue? #(empty? @front-ptr)
        front #(if (empty-queue?)
                 (throw (Exception. "FRONT is called with an empty queue"))
                 (first @front-ptr))
        print-queue #(loop [ptr front-ptr acc '()]
                       (if (empty? @ptr)
                         (reverse acc)
                         (recur (last @ptr) (conj acc (first @ptr)))))
        insert-queue! #(let [new-pair (list % (atom '()))]
                         (if (empty-queue?)
                           (do
                             (reset! front-ptr new-pair)
                             (reset! rear-ptr new-pair)
                             (print-queue))
                           (do
                             (reset! (last @rear-ptr) new-pair)
                             (reset! rear-ptr new-pair)
                             (print-queue))))
        delete-queue! #(if (empty-queue?)
                         (throw (Exception. "DELETE called with empty list"))
                         (do
                           (reset! front-ptr @(last @front-ptr))
                           (print-queue)))]
    (fn [op]
      (cond (= op 'empty?) empty-queue?
            (= op 'front) front
            (= op 'insert!) insert-queue!
            (= op 'delete!) delete-queue!
            :else (throw (Exception. "Operation does not exist"))))))