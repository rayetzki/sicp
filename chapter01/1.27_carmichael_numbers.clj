(require '[lib.fermat :refer [fast-prime?, expmod]])

;; Carmichael numbers: 561, 1105, 1729, 2465, 2821, 6601

(fast-prime? 561 4 expmod)
(fast-prime? 1105 3 expmod)
(fast-prime? 1729 10 expmod)
(fast-prime? 2465 10 expmod)
(fast-prime? 2821 10 expmod)
(fast-prime? 6601 10000 expmod)