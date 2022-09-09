;; the (make-account 50) call result is bound to acc name. Inner vars are in context of acc
;; the (make-account 100) is bound to acc2, and all it's inner callings are made in context of acc2. So acc and acc2 are 2 separate contexts for the same computations.
;; make-account definition (it's params, body) is the only one common between acc1 and acc2