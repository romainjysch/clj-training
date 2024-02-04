(ns getting-clojure.chapter_1)

;; Chapter 1 : Hello, Clojure

(defn hello []
  (println "Hello"))
(hello)

(defn hello-world []
  (println "Hello, World!"))
(hello-world)

(defn foo
  "I don't do a whole lot."
  [x]
  (println "Hello" x "!"))
(foo "Clojure")

(defn hello-to [someone]
  (println "Hello" someone))
(hello-to "Romain")
(hello-to nil)

(str "Hello, " "world " "!")
(str "Clo" "jure")

(count "Clojure")

(/ 8 3)
(/ 8 2)
(+ 10 10.233 10.55)
(/ 10 0) ; ArithmeticException

;; Symbol and value, binding
(def firstname "Romain")
(println firstname)
(def the-average (/ (+ 10 30.0) 2))
(println the-average)

(defn chatty-average [a b]
  (println "chatty-average function called")
  (println "** first argument:" a)
  (println "** second arugment:" b)
  (/ (+ a b) 2))
(chatty-average 10 30)
(catty-average 10 30) ; syntax error
chatty-average ; everything is a value

(def numbers [5 6 4 6 9])
(count numbers)
(reduce + numbers)

(defn another-average [x]
  (/ (reduce + x) (count x)))
(another-average numbers)

;; Always two spaces, no tabs allowed
;; (declare ...) for sticky situations
;; The last def or defn wins