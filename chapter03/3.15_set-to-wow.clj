;; first el of the pair is set to 'wow if (cons x x)
;; x is an address, so it gets mutated for both els of pair
;; otherwise, (cons (list 'a 'b) (list 'a 'b)) are different in terms of memory, though they have equal values
;; for second case, (cons (list 'wow b) (list 'a 'b)) changes only first el of the first pair