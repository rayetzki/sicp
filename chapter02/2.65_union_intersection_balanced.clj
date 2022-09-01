(require '[chapter02.2.61_adjoin_set_ordered :refer [intersection-set]])
(require '[chapter02.2.62_union_set_ordered :refer [union-set]])
(require '[chapter02.2.63_tree_list :refer [tree]])
(require '[chapter02.2.64_balanced_tree :refer [list->tree]])

(defn balanced-union-set [s1 s2]
  (list->tree (union-set (tree s1) (tree s2))))

(defn balanced-intersection-set [s1 s2]
  (list->tree (intersection-set (tree s1) (tree s2))))

(get 'make 'rational)