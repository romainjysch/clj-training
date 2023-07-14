(ns clj-training.chapter_1)

;; Chapter 1 : Hello, Clojure

(defn hello-world []
  (println "Hello, World!"))
(hello-world)

(defn foo
  "I don't do a whole lot."
  [x]
  (println "Hello" x "!"))
(foo "Clojure")

(defn chatty-average [a b]
  (println "chatty-average function called")
  (println "** first argument:" a)
  (println "** second arugment:" b)
  (/ (+ a b) 2))
(chatty-average 10 30)