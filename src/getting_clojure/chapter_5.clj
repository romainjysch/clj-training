(ns getting-clojure.chapter-5)

;; Chapter 5 : More Capable Functions

(defn greet ; multy-arity function - max 3 or 4
  ([to] (println "Hello" to))
  ([message to] (println message to))
  ([message1 message2 to] (println message1 message2 to)))
(greet "Romain")
(greet "Bonjour" "Romain")
(greet "Hello" "and welcome" "Romain")

(defn greet-v2
  "Multi-arity function example."
  ([to] (greet-v2 "Hello" to))
  ([message to] (println message to)))
(greet-v2 "Romain")
(greet-v2 "Bonjour" "Romain")

(defn print-any-args [& args]
  "Print arguments list"
  (println "Collection of args:" args))
(print-any-args "Romain" 2 3 4)
(print-any-args true :keyword 2 "Romain")

(defn first-arg [& args]
  (first args))
(first-arg "Romain" 2 3 4)

(defn rest-args [& args]
  (rest args))
(rest-args "Romain" "Robin" "Victor")

(defn first-arg-v2 [x & args] ; x + multiple arguments
  "Varadic function example."
  (println "Args:" x (first args)))
(first-arg-v2 "Romain" 2 3 4)

(defn first-arg-v3 [x & args]
  "Varadic function example."
  (println "Args:" x (rest args)))
(first-arg-v3 "Romain" "Victor" 2 3 4)
(first-arg-v3 "Romain") ; Args: Romain ()
(first-arg-v3 "Romain" "Victor") ; Args: Romain ()
(first-arg-v3 "Romain" "Victor" "Robin") ; Args: Romain (Robin)

;; & args functions are variadic functions and they have a single function body
;; multi-arity functions have multiple bodies

(def book1 {:title "Dune" :author "Herbert"})
(def book2 {:book "Dune" :by "Herbert"})
(def book3 ["Dune" "Herbert"])

(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map-book
    (contains? book :book) :alternative-map-book))
(dispatch-book-format book1)
(dispatch-book-format book2)
(dispatch-book-format book3)

(defmulti normalize-book dispatch-book-format) ; multimethod
;; normalize-book will be the name of the different implementations
(defmethod normalize-book :vector-book [book] ; define first implementation of normalize-book
  {:title (first book) :author (second book)})
(defmethod normalize-book :standard-map-book [book] ; define second implementation
  book)
(defmethod normalize-book :alternative-map-book [book] ; define third implementation
  {:title (book :book) :author (book :by)})
(normalize-book book1)
(normalize-book book2)
(normalize-book book3)

(def books [{:title "Dune" :author "Herbert" :genre :sf}
            {:title "Harry Potter" :author "Rowling" :genre :teens}])

(defmulti book-description :genre)
(defmethod book-description :sf [book]
  (str "SF story by " (book :author)))
(defmethod book-description :teens [book]
  (str "Story for teenagers by " (book :author)))
(book-description (books 0))
(book-description (books 1))

(def books-copies
  [{:title "Jaws" :copies-sold 2000000}
   {:title "Emma" :copies-sold 3000000}
   {:title "2001" :copies-sold 4000000}])

(defn sum-copies
  ([books] (sum-copies books 0)) ; calling the function just with a vector
  ([books total]
   (if (empty? books)
     total ; if empty, return the total
     (recur ; if not, tail call optimization of this function
       (rest books)
       (+ total (:copies-sold (first books)))))))
(sum-copies books-copies)

(defn sum-copies-v2 [books]
  (loop [books books total 0]
    (if (empty? books)
      total
      (recur
        (rest books)
        (+ total (:copies-sold (first books)))))))
(sum-copies-v2 books-copies)

(defn sum-copies-v3 [books] ; the best one
  (apply + (map :copies-sold books)))
(sum-copies-v3 books-copies)
(map :copies-sold books-copies)

(defn multi-average
  "Return the average of 2 or 3 numbers."
  ([x y] (/ (+ x y) 2.0))
  ([x y z] (/ (+ x y z) 3.0)))
(multi-average 10 20)
(multi-average 10 20 30)

(defn print-book [book]
  (when-not (contains? book :title)
    (throw (ex-info "Book must contains a :title key" {:book book})))
  (println book))
(print-book book1)
(print-book book2)

(defn print-book-v2 [book]
  {:pre [(:title book) (:author book)]} ; runtime exception if not truthy
  (println book))
(print-book-v2 book1)
(print-book-v2 book2)

(defn one-two-or-more
  ([a] (println "One arg:" a))
  ([a b] (println "Two args:" a b))
  ([a b & args] (println "More than two:" a b args)))
(one-two-or-more "Romain")
(one-two-or-more "Romain" "Robin")
(one-two-or-more "Romain"  "Robin" 2 3 4) ; More than two: Romain Robin (2 3 4)