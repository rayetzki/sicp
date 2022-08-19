(defn a-plus-abs-b [a b]
  ((if (pos? b) + -) a b))

(a-plus-abs-b 2 -2)