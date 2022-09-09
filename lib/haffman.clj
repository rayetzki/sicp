(ns lib.haffman)

(defn make-leaf [symb weight] (list 'leaf symb weight))
(defn get-leaf-symbol [leaf] (nth leaf 1))
(defn get-leaf-weight [leaf] (nth leaf 2))
(defn leaf? [object] (= (first object) 'leaf))

(defn left-branch [tree] (first tree))
(defn right-branch [tree] (second tree))

(defn symbols [node]
  (if (leaf? node)
    (list (get-leaf-symbol node))
    (nth node 2)))

(defn weights [node]
  (if (leaf? node)
    (get-leaf-weight node)
    (nth node 3)))

(defn adjoin-set [el items]
  (loop [[first-leaf & other :as st] items result (empty items)]
    (cond (empty? st) (-> el (cons result) reverse)
          (= el first-leaf) items
          (< (weights el) (weights first-leaf)) (-> el (cons result) reverse (concat st))
          :else (recur other (cons first-leaf result)))))

(defn make-code-tree [left right]
  (list left
        right
        (concat (symbols left) (symbols right))
        (+ (weights left) (weights right))))

(defn element-of-set? [el items]
  (cond (empty? items) false
        (= el (first items)) true
        :else (element-of-set? el (rest items))))

(defn make-leaf-set [pairs]
  (if (empty? pairs)
    (empty pairs)
    (let [[symb value] (first pairs) other (rest pairs)]
      (adjoin-set (make-leaf symb value)
                  (make-leaf-set other)))))

(defn- choose-branch [bit tree]
  (cond (= 0 bit) (left-branch tree)
        (= 1 bit) (right-branch tree)
        :else (throw (Exception. "Bad bit provided -- CHOOSE-BRANCH" bit))))

(defn decode [message tree]
  (loop [[first-bit & rest-bits :as bits] message current-branch tree acc '()]
    (if (empty? bits)
      (reverse acc)
      (let [next-branch (choose-branch first-bit current-branch)]
        (if (leaf? next-branch)
          (recur rest-bits tree (cons (get-leaf-symbol next-branch) acc))
          (recur rest-bits next-branch acc))))))

(defn encode-symbol [tree sym]
  (loop [node tree bits (empty tree)]
    (if (leaf? node)
      (reverse bits)
      (let [left (left-branch node) right (right-branch node)
            next-branch? #(->> % symbols (element-of-set? sym))]
        (cond (next-branch? left) (recur left (cons 0 bits))
              (next-branch? right) (recur right (cons 1 bits))
              :else (throw (Exception. "No symbol in the tree" sym)))))))

(defn encode [tree message]
  (loop [[head & tail] message encoded-message (empty tree)]
    (if (nil? head)
      encoded-message
      (recur tail (concat encoded-message (encode-symbol tree head))))))