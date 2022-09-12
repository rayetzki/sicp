(defn make-queue [] (list (atom (empty list)) (atom (empty list))))
(defn front-ptr [queue] (first queue))
(defn rear-ptr [queue] (last queue))
(defn set-front-ptr! [queue item] (reset! (front-ptr queue) item))
(defn set-rear-ptr! [queue item] (reset! (rear-ptr queue) item))
(defn empty-queue? [queue] (empty? @(front-ptr queue)))

(defn front-queue [queue]
  (if (empty-queue? queue)
    (throw (Exception. "FRONT is called with an empty queue"))
    (first @(front-ptr queue))))

(defn print-queue [queue]
  (loop [ptr (front-ptr queue) acc (empty list)]
    (if (empty? @ptr)
      (reverse acc)
      (recur (last @ptr) (conj acc (first @ptr))))))

(defn insert-queue! [queue item]
  (let [new-pair (list item (atom (empty list)))]
    (if (empty-queue? queue)
      (do
        (set-front-ptr! queue new-pair)
        (set-rear-ptr! queue new-pair)
        (print-queue queue))
      (do
        (reset! (last @(rear-ptr queue)) new-pair)
        (set-rear-ptr! queue new-pair)
        (print-queue queue)))))

(defn delete-queue! [queue]
  (if (empty-queue? queue)
    (throw (Exception. "DELETE called with empty list"))
    (do
      (set-front-ptr! queue @(last @(front-ptr queue)))
      (print-queue queue))))
