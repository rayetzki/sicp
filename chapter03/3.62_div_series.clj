(require '[lib.stream :refer [stream-value]])
(require '[chapter03.3.59_integrate_series :refer [sine-series, cosine-series]])
(require '[chapter03.3.60_mul_series :refer [mul-series]])
(require '[chapter03.3.61_invert_unit_series :refer [invert-unit-series]])

(defn div-series [s1 s2]
  (when (zero? (stream-value s2))
    (throw (Exception. "Denominator is zero in stream DIV--SERIES")))
  (mul-series s1 (invert-unit-series s2)))

(def tg-series (div-series sine-series cosine-series))