(require '[lib.sum :refer [sum-iter, sum-linear-rec]])
(require '[lib.math :refer [cube]])
(require '[chapter01.1.29_simpsons_rule :refer [simpsons-integral, integral]])

(print "Regular integral calc iter vs recursive")
(time (integral cube 0 1 0.001 sum-iter))
(time (integral cube 0 1 0.001 sum-linear-rec))

(print "Simpsons' integral calc iter vs recursive")
(time (simpsons-integral cube 0 1 5000 sum-iter))
(time (simpsons-integral cube 0 1 5000 sum-linear-rec))