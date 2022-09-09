;; The behaviour will be same, but with one more extra frame to save initial amount
;; So, previously E1 held parameter balance bound to make-withdraw function. Result of body computation used to re-assign balance param and return it back;
;; After binding initial-amount to inner ``balance`` variable, there is no re-assignment of parameter after body computation. So ``balance`` is an inner state.
;; In this case, the function is pure.