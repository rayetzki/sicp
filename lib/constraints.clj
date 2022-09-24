(ns lib.constraints)

(defn error [& args]
  (throw (Exception. (apply str args))))
(defn get-value [connector] (connector :value))
(defn has-value? [connector] (connector :has-value?))
(defn set-value! [connector value informant] ((connector :set-value) value informant))
(defn forget-value! [connector retractor] ((connector :forget) retractor))
(defn connect [connector constraint] ((connector :connect) constraint))

(defn inform-about-value [constraint] (constraint :I-have-a-value))
(defn inform-about-no-value [constraint] (constraint :I-lost-my-value))

(defn for-each-except [exception proc arr]
  (loop [items arr]
    (cond (empty? items) 'done
          (= (first items) exception) (recur (rest items))
          :else (do
                  (proc (first items))
                  (recur (rest items))))))

(defn make-connector []
  (let [value (atom nil) informant (atom nil) constraints (atom [])]
    (letfn [(set-value [new-value setter]
              (cond (not (has-value? me)) (do
                                            (reset! value new-value)
                                            (reset! informant setter)
                                            (for-each-except setter inform-about-value @constraints))
                    (not= @value new-value) (error "Contradiction" [@value new-value])))
            (forget-value [retractor]
              (when (= retractor @informant)
                (reset! informant false)
                (for-each-except retractor inform-about-no-value @constraints)))
            (connect [new-constraint]
              (when-not (some #(= % new-constraint) @constraints)
                (swap! constraints conj new-constraint))
              (when (has-value? me)
                (inform-about-no-value new-constraint)))
            (me [request]
              (cond (= request :has-value?) (if @informant true false)
                    (= request :value) @value
                    (= request :set-value) set-value
                    (= request :forget) forget-value
                    (= request :connect) connect
                    :else (error "Unknown operation -- MAKE CONNECTOR" request)))]
      me)))

(defn adder [a1 a2 sum]
  (letfn [(process-new-value []
            (cond (and (has-value? a1) (has-value? a2)) (set-value! sum (+ (get-value a1) (get-value a2)) me)
                  (and (has-value? a1) (has-value? sum)) (set-value! a2 (- (get-value sum) (get-value a1)) me)
                  (and (has-value? a2) (has-value? sum)) (set-value! a1 (- (get-value sum) (get-value a2)) me)))
          (process-forget-value []
            (forget-value! sum me)
            (forget-value! a1 me)
            (forget-value! a2 me))
          (me [request]
            (cond (= request :I-have-a-value) (process-new-value)
                  (= request :I-lost-my-value) (process-forget-value)
                  :else (error "Unknown request -- ADDER" request)))]
    (connect a1 me)
    (connect a2 me)
    (connect sum me)
    me))

(defn multiplier [m1 m2 product]
  (letfn [(process-new-value []
            (cond (or (and (has-value? m1) (zero? (get-value m1)))
                      (and (has-value? m2) (zero? (get-value m2)))) (set-value! product 0 me)
                  (and (has-value? m1) (has-value? m2)) (set-value! product (* (get-value m1) (get-value m2)) me)
                  (and (has-value? product) (has-value? m1)) (set-value! m2 (/ (get-value product) (get-value m1)) me)
                  (and (has-value? product) (has-value? m2)) (set-value! m1 (/ (get-value product) (get-value m2)) me)))
          (process-forget-value []
            (forget-value! product me)
            (forget-value! m1 me)
            (forget-value! m2 me)
            (process-new-value))
          (me [request]
            (cond (= request :I-have-a-value) (process-new-value)
                  (= request :I-lost-my-value) (process-forget-value)
                  :else (error "Unknown request -- MULTIPLIER" request)))]
    (connect m1 me)
    (connect m2 me)
    (connect product me)
    me))

(defn constant [value connector]
  (letfn [(me [request] (error "Unknown request" request))]
    (connect connector me)
    (set-value! connector value me)
    me))

(defn probe [name connector]
  (letfn [(print-probe [value] (println (str "Probe: " name " = " value)))
          (process-new-value [] (print-probe (get-value connector)))
          (process-forget-value [] (print-probe "?"))
          (me [request]
            (cond (= request :I-have-a-value) (process-new-value)
                  (= request :I-lost-my-value) (process-forget-value)
                  :else (error "Unknown request" request)))]
    (connect connector me)
    me))

(defn celcius-fahrenheit-converter [C F]
  (let [u (make-connector)
        v (make-connector)
        w (make-connector)
        x (make-connector)
        y (make-connector)]
    (multiplier C w u)
    (multiplier v x u)
    (adder v y F)
    (constant 9 w)
    (constant 5 x)
    (constant 32 y)))

(def C (make-connector))
(def F (make-connector))
(probe "Celcius: " C)
(probe "Fahrenheit: " F)
(celcius-fahrenheit-converter C F)
(set-value! C 24 'user)