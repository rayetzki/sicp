(defn make-mobile [left right] (list left right))
(defn make-branch [length structure] (list length structure))

(defn left-branch [mobile] (first mobile))
(defn right-branch [mobile] (last mobile))
(defn branch-length [branch] (first branch))
(defn branch-structure [branch] (last branch))
(defn mobile? [branch] (list? branch))

(defn total-weight [mobile]
  (let [left (left-branch mobile)
        right (right-branch mobile)
        branch-weight #(let [structure (branch-structure %)]
                         (if-not (mobile? structure)
                           structure
                           (total-weight structure)))]
    (+ (branch-weight left) (branch-weight right))))

(defn is-balanced? [mobile]
  (let [weight (total-weight mobile)
        left (left-branch mobile)
        right (right-branch mobile)
        branch-weight #(let [structure (branch-structure %)]
                         (if-not (mobile? structure)
                           structure
                           (total-weight structure)))
        balanced-branch? #(let [structure (branch-structure %)]
                            (if-not (mobile? structure)
                              structure
                              (is-balanced? structure)))]
    (and
     (balanced-branch? left)
     (balanced-branch? right)
     (=
      (* (branch-weight left) weight)
      (* (branch-weight right) weight)))))

