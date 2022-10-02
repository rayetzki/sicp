(require '[lib.serializer :refer [make-mutex]])

(defn make-semaphore-mutex [n]
  (let [left (atom 0) mutex (make-mutex)]
    (letfn [(acquire []
              (mutex 'acquire)
              (if (< @left n)
                (do
                  (swap! left + 1)
                  (mutex 'release))
                (do
                  (mutex 'release)
                  (acquire))))
            (release []
              (mutex 'acquire)
              (when-not (zero? @left)
                (swap! left + 1)
                (mutex 'release)))]
      (fn [m]
        (cond (= 'acquire m) acquire
              (= 'release m) release
              :else (throw (Error. "Unknown operation -- MAKE-SEMAPHORE", m)))))))

(defn make-semaphore-plain [n]
  (let [c (atom 0)
        cell (atom false)]
    (letfn [(acquire []
              (if (compare-and-set! cell true false)
                (acquire)
                (if (< @c n)
                  (do
                    (swap! c + 1)
                    (reset! cell false))
                  (do
                    (reset! cell false)
                    (acquire)))))
            (release []
              (if (compare-and-set! cell true false)
                (release)
                (do
                  (when-not (zero? @c) (swap! c - 1))
                  (reset! cell false))))]
      (fn [m]
        (cond (= m 'acquire) acquire
              (= m 'release) release
              :else (throw (Error. "Unknown message sent to semaphore -- MAKE-SEMAPHORE" m)))))))