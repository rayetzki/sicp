(require '[lib.fermat :refer [fermat-test, expmod]])
(import java.lang.System)
(require '[chapter01.1.22_search_for_primes :refer [search-for-primes, start-prime-test, timed-prime-test]])

(defn fast-timed-prime-test [n]
  (start-prime-test n (System/nanoTime) fermat-test expmod))

(time (search-for-primes 1000 30 timed-prime-test))
(time (search-for-primes 1000 30 fast-timed-prime-test))