(ns learn-clojure.flow-control)

;; when
(when (neg? 2)
  (throw (RuntimeException. (str "x must be positive"))))
(when (neg? -1)
  (throw (RuntimeException. (str "x must be positive"))))

;; cond
(let [x 5]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10"))

;; case
(defn foo [x]
  (case x
    5 "5"
    10 "10"))
(foo 10)
(foo 11) ; throw an exception

(defn foo-v2 [x]
  (case x
    5 "5"
    10 "10"
    "no match"))
(foo-v2 11)

;; dotimes
(dotimes [i 3]
  (println i))

;; doseq
(doseq [n (range 3)]
  (println n))

(doseq [letter [:a :b]
        number (range 3)]
  (prn [letter number]))

;; for
(for [letter [:a :b]
      number (range 3)]
  [letter number])

;; recur
(loop [i 0]
  (if (< i 10)
    (recur (inc i))
    i))

(defn increase [i]
  (if (< i 10)
    (recur (inc i))
    i))
(increase 11)

;; exceptions
(defn divide-two-numbers
  [x y]
  (try
    (/ x y)
    (catch ArithmeticException e
      "divide by zero")
    (finally
      "cleanup")))
(divide-two-numbers 2 1)
(divide-two-numbers 4 2)
(divide-two-numbers 4 0)

(try
  (throw (ex-info "There was a problem" {:detail 42}))
  (catch Exception e
    (prn (:detail (ex-data e)))))