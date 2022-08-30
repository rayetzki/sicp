(def empty-board (empty list))

(defn safe? [positions]
  (let [new-pair (first positions)
        safe-in-row? (fn [cells]
                       (empty? 
                        (filter (fn [cell] (= (first new-pair) (first cell))) cells)))
        safe-in-diagonal? (fn [cells]
                            (let [queen (first cells)]
                              (cond
                                (empty? cells) true
                                (= (abs (- (first new-pair) (first queen)))
                                   (abs (- (last new-pair) (last queen)))) false
                                :else (recur (rest cells)))))]
    (and
     (safe-in-row? (rest positions))
     (safe-in-diagonal? (rest positions)))))

(defn adjoin-position [cell rest-of-queens]
  (cons cell rest-of-queens))

(defn queen-cols [col board-size]
  (if (zero? col)
    (list empty-board)
    (filter (fn [positions] (safe? positions))
            (mapcat (fn [rest-of-queens]
                      (map (fn [row]
                             (adjoin-position (list row col) rest-of-queens))
                           (range 1 (inc board-size))))
                    (queen-cols (dec col) board-size)))))

(defn queens [board-size]
  (queen-cols board-size board-size))

;; Original complexity O(n * n!))
;; Hugos error: O(n ^ n) - exponential growth