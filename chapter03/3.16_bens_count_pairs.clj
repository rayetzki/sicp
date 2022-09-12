;; Never finishes - if pair has a pointer to some other pair
;; Has length of 3: (list 'a 'b 'c)
;; Has length of 4: (list (list 'a) (list 'a))
;; Has length of 7: (list (list 'a (list 'a)) (list 'a (list 'a))))