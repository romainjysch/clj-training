(ns getting-clojure.chapter-8)

;; Chapter 8 : Def, Symbols and Vars

(def dune {:title "Dune" :author "Herbert" :isbn "9780441172719"})

(defn book-description [book]
  (str (book :title)
       " written by "
       (book :author)))
(book-description dune)

(def ISBN-LENGTH 13)
(def OLD-ISBN-LENGTH 10)
(def isbn-lengths [ISBN-LENGTH OLD-ISBN-LENGTH])

(defn valid-isbn? [isbn]
  (or (= (count isbn) ISBN-LENGTH)
      (= (count isbn) OLD-ISBN-LENGTH)))
(valid-isbn? (dune :isbn))
(valid-isbn? "978-0441172719")

(str 'ISBN-LENGTH)
'ISBN-LENGTH
(= 'ISBN-LENGTH 'ISBN-LENGTH)
(= 'ISBN-LENGTH 'OLD-ISBN-LENGTH)

(def ^:dynamic *debug-enabled* false)
(println *debug-enabled*)

(defn debug [msg]
  (if (= *debug-enabled* true)
    (println msg)
    (println "debug disabled")))

(debug "Hello")

(binding [*debug-enabled* true]
  (debug "hello"))

(def numbers [1 2 3 4 5 6 7 8 9 10])
(set! *print-length* 3)
(println numbers)

(+ 1 1)
*1
*2
*e