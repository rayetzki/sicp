(def frst (list 1 3 (list 5 7) 9))
(def secnd (list (list 7)))
(def thrd (list 1 (list 2 (list 3 (list 4 (list 5 (list 6 7)))))))

(last (rest (rest frst)))
(last (last secnd))
(last (last (last (last (last (last thrd))))))
