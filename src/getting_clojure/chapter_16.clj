(ns getting-clojure.chapter_16
  (:import java.io.File
           com.google.gson.Gson))

;; Chapter 16 : Interoperating with Java

(def authors (java.io.File. "authors.txt"))

(if (.exists authors)
  (println "File created")
  (println "File not created"))

(if (.canRead authors) ; instance method
  (println "Readable!"))

(.setReadable authors true)

(def rect (java.awt.Rectangle. 0 0 10 20))

(println "Width:" (.-width rect))
(println "Height:" (.-height rect))

(def authors-v2 (File. "authors-v2.txt")) ; constructor

String
Boolean

File/separator

;; static methods -> class/method
(def temp-authors-file (File/createTempFile "authors_list" ".txt"))

(def v [1 2 3 4])
(.count v) ; call a Java method on Clojure

(def author "Herbert") ; symbol value
(def the-var #'author)
(.get the-var)
(.-sym the-var)

(cons 99 [1 2 3])
(def c (cons 99 [1 2 3]))
(class c)
(.first c)
(.more c)

;; .method and class/staticMethod in place of functions, they are not functions
;; they are, instead, special forms, more or less hardwired into Clojure