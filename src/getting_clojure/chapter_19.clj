(ns getting-clojure.chapter-19)

;; Chapter 19 : Read and Eval

'(defn print-greeting [preferred-customer]
   (if preferred-customer
     (println "Welcome back!")))

(defn print-greeting [preferred-customer]
  (if preferred-customer
    (println "Welcome back!")))

;; code is data in Clojure
;; function calls are lists
;; homoiconic
;; Clojure code is just Clojure data, all the way down

(read)

(def s
  "(defn print-greeting [preferred-customer]
  (if preferred-customer (println \"Welcome back!\")))")
(read-string s)

(def a-data-structure '(+ 2 2))
(eval a-data-structure)

(def some-data
  '(defn print-greeting [preferred-customer]
     (if preferred-customer (println "Welcome back!"))))
(eval some-data)
(print-greeting true)

(eval 55)
(eval :hello)
(eval "hello")

(def title "Dune")
(def the-symbol title)
(eval the-symbol)
(eval '(count title))

;; eval runs stuff as code

(defn russ-repl []
  (loop []
    (println (eval (read)))
    (recur)))
(russ-repl)

(defn add5
  "Add 5 to the specified number"
  [x]
  (+ x 5))
(meta #'add5)
(add5 5)