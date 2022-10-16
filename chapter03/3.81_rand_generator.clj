(require '[lib.stream :refer [cons-stream, stream-map, stream->list]])

(defn rand-update [x] (mod (+ 101 (* x 713)) 53))
(def random-init (rand-update 10))

(defn random-request-generator [requests]
  (letfn [(update [x request]
            (cond (= request 'generate) (rand-update x)
                  (and (list? request) (= (first request) 'reset) (number? (second request))) (second request)
                  :else (throw (Exception. "Invalid request"))))]
    #_{:clj-kondo/ignore [:inline-def]}
    (def requested-stream
      (cons-stream random-init #(stream-map update requested-stream requests)))
    requested-stream))

(def gen-stream
  (cons-stream '(reset 12)
               (fn [] (cons-stream 'generate
                                   (fn [] (cons-stream '(reset 100)
                                                       (fn [] (cons-stream 'generate
                                                                           gen-stream))))))))

(stream->list (random-request-generator gen-stream) 4)