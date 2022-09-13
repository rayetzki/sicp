(defrecord Node [value next prev])
(defn make-node [value] (Node. (atom value) (atom nil) (atom nil)))
(defn value-node [node] @(:value node))
(defn next-node [node] @(:next node))
(defn prev-node [node] @(:prev node))
(defn set-prev-node! [node value] (reset! (:prev node) value))
(defn set-next-node! [node next] (reset! (:next node) next))

(defrecord Deque [front rear])
(defn front [deque] @(:front deque))
(defn rear [deque] @(:rear deque))
(defn set-front! [deque front] (reset! (:front deque) front))
(defn set-rear! [deque rear] (reset! (:rear deque) rear))

(defn make-deque [] (Deque. (atom nil) (atom nil)))
(defn empty-deque? [deque] (empty? (front deque)))

(defn front-deque [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (value-node (front deque))))

(defn rear-deque [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (value-node (rear deque))))

(defn print-deque [deque]
  (loop [node (front deque) acc '()]
    (if (empty? node)
      (reverse acc)
      (recur (next-node node) (conj acc (value-node node))))))

(defn- insert-in-empty-deque! [deque value]
  (set-front! deque value)
  (set-rear! deque value)
  (print-deque deque))

(defn front-insert-deque! [deque item]
  (let [new-node (make-node item)]
    (if (empty-deque? deque)
      (insert-in-empty-deque! deque new-node)
      (do (set-prev-node! (front deque) new-node)
          (set-next-node! new-node (front deque))
          (set-front! deque new-node)
          (print-deque deque)))))

(defn rear-insert-deque! [deque item]
  (let [new-node (make-node item)]
    (if (empty-deque? deque)
      (insert-in-empty-deque! deque new-node)
      (do (set-next-node! (rear deque) new-node)
          (set-prev-node! new-node (rear deque))
          (set-rear! deque new-node)
          (print-deque deque)))))

(defn front-delete-deque! [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (let [node (next-node (front deque))]
      (if (empty? node)
        (do (set-front! deque nil)
            (set-rear! deque nil)
            (print-deque deque))
        (do (set-prev-node! node nil)
            (set-front! deque node)
            (print-deque deque))))))

(defn rear-delete-deque! [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (let [prev-node (prev-node (rear deque))]
      (if (empty? prev-node)
        (do (set-front! deque nil)
            (set-rear! deque nil)
            (print-deque deque))
        (do (set-next-node! prev-node nil)
            (set-rear! deque prev-node)
            (print-deque deque))))))

(def deq (make-deque))
(front-insert-deque! deq 'a)
(rear-insert-deque! deq 'c)
(rear-delete-deque! deq)