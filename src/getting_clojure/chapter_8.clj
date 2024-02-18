(ns getting-clojure.chapter-8)

;; Chapter 8 : Def, Symbols and Vars

;; def function is perfect for constants
(def PI 3.14)

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

'author
'title
(str 'author) ; turn a symbol in a string

(str 'ISBN-LENGTH)
'ISBN-LENGTH
(= 'ISBN-LENGTH 'ISBN-LENGTH)
(= 'ISBN-LENGTH 'OLD-ISBN-LENGTH)

;; var has two slots : one for the symbol and the other for the value
(def title "Dune")

(defn compute-pi [diameter]
  (* PI diameter))
(compute-pi 2) ; return 6.28
(def PI 3.14159)
(compute-pi 2) ; now return 6.28318

;; dynamic var
(def ^:dynamic *debug-enabled* false) ; * * is convention for dynamic var
(println *debug-enabled*)

(defn debug [msg]
  (if (= *debug-enabled* true)
    msg
    "debug disabled"))

(debug "Hello")

;; binding expression looks a lot like let
(binding [*debug-enabled* true]
  (debug "hello"))

(def numbers [1 2 3 4 5 6 7 8 9 10])
(first (next numbers)) ; is like the second function
(next numbers)

(set! *print-length* 3)
(println numbers)

(set! *print-length* nil)
(println numbers)

(binding [*print-length* 5]
  (println numbers))

(+ 1 1)
*1
*2
*e ; last exception