(require '[lib.set :refer [adjoin-set]])

(defn union-set [S1 S2]
  (reduce #(adjoin-set %2 %1) S1 S2))