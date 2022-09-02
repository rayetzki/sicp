(require '[chapter02.2.61_adjoin_set_ordered :refer [adjoin-set]])

(defn union-set [S1 S2]
  (reduce #(adjoin-set %2 %1) S1 S2))