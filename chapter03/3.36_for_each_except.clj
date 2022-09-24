(require '[lib.constraints :refer [set-value!, make-connector]])

(def a (make-connector))
(def b (make-connector))
(set-value! a 10 'user)

;; --> check if connector has-value (empty if set after creation)
;; --> set it to 10
;; --> set informant to 'user
;; --> return done since constraints is an empty array

;; connector
;; local vars: value (atom nil), informant (atom nil), constraints []
;; (has-value? value) --> false
;; (reset! value 10) --> sets value to 10
;; (for-each-except 'user [] inform-about-value)
;; :done --> empty array