(ns getting-clojure.chapter-17)

;; Chapter 17 : Threads, Promises, and Futures

(defn do-something-in-a-thread []
  (println "Start other thread...")
  (Thread/sleep 3000)
  (println "Stop other thread..."))
(def the-other-thread (Thread. do-something-in-a-thread))
(.start the-other-thread)

(defn first-thread []
  (println "Start first thread")
  (Thread/sleep 2000)
  (println "Stop first thread"))
(def the-first-thread (Thread. first-thread))

(defn second-thread []
  (println "Start second thread")
  (Thread/sleep 2000)
  (println "Stop second thread"))
(def the-second-thread (Thread. second-thread))

(defn run-two-threads []
  (.start the-first-thread)
  (.start the-second-thread)
  (println "Print something while waiting"))
(run-two-threads)

(def fav-book "Dune")
(defn make-dune-fav-book []
  (def fav-book "Dune"))
(make-dune-fav-book)
(defn make-1984-fav-book []
  (def fav-book "1984"))
(make-1984-fav-book)
(println fav-book)
(defn change-fav-book []
  (.start (Thread. make-1984-fav-book))
  (.start (Thread. make-dune-fav-book))
  (println "Fav book is :" fav-book))
(change-fav-book) ; Dune is sometimes the fav book...
;; threads are making changes to a shared resource

(def del-thread (Thread. #(.delete (java.io.File. "hello.txt"))))
(.start del-thread)
(.join del-thread)

;; promise and deref or @ for immuability and wait until the thread is finished

(def the-result (promise)) ; returns a promise object that can be read with deref/@
(realized? the-result)
(def the-other-result (promise))
(realized? the-other-result)
(deliver the-result "Dune") ; set only once with deliver
(println "The value in the promise is :" (deref the-result))
(println "The value in the promise is :" @the-result)

(def inventory [{:title "Dune" :sold 51 :revenue 255}
                {:title "1984" :sold 17 :revenue 170}])

(defn sum-copies-sold [inv]
  (apply + (map :sold inv)))
(sum-copies-sold inventory)

(defn sum-revenue [inv]
  (apply + (map :revenue inv)))
(sum-revenue inventory)

(defn compute-sums [inv]
  (let [copies-promise (promise)
        revenue-promise (promise)]
    (.start (Thread. #(deliver copies-promise (sum-copies-sold inv))))
    (.start (Thread. #(deliver revenue-promise (sum-revenue inv))))
    (println "Other random stuff...")
    (println "Total books sold :" (deref copies-promise)) ; using deref
    (println "Total revenue :" @revenue-promise))) ; using @
(compute-sums inventory)

(def revenue-future
  (future (apply + (map :revenue inventory)))) ; future evaluate the function in a separate thread
(println "Total revenue is" @revenue-future)

(def a-future (future
                (Thread/sleep 5000)
                (println "done")
                100)) ; cache the result
@a-future ; first time we have to wait
@a-future ; second time, no waiting

(apply + (pmap :revenue inventory)) ; pmap for computationally intensive functions