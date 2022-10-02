;; Atomic means one value at a time, so it guarantess that for both process the value is same
;; If one process acquires atomic and sets it, the second process knows the current value once acquired


;; When mutex value isn`t handled atomically, check and set is done separately, which is a source of interleaving for other processes.
;; So, check and set must be implemented as a single operation. Clojure version: compare-and-set!