(ns getting-clojure.chapter-5)

;; Chapter 5 : More Capable Functions

(defn greet
  ([to] (println "Hello" to))
  ([message to] (println message to)))
(greet "Romain")
(greet "Bonjour" "Romain")

(defn greet-v2
  "Multi-arity function example."
  ([to] (greet-v2 "Hello" to))
  ([message to] (println message to)))
(greet-v2 "Romain")
(greet-v2 "Bonjour" "Romain")

(defn print-any-args [& args]
  (println "Collection of args:" args))
(print-any-args "Romain" 2 3 4)

(defn first-arg [& args]
  (first args))
(first-arg "Romain" 2 3 4)

(defn first-arg-v2 [x & args]
  "Varadic function example."
  (println "Args:" x (first args)))
(first-arg-v2 "Romain" 2 3 4)

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

(defmulti normalize-book dispatch-book-format)
(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})
(defmethod normalize-book :standard-map-book [book]
  book)
(defmethod normalize-book :alternative-map-book [book]
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
  ([books] (sum-copies books 0))
  ([books total]
   (if (empty? books)
     total
     (recur
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

(defn sum-copies-v3 [books]
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
  {:pre [(:title book) (:author book)]}
  (println book))
(print-book-v2 book1)
(print-book-v2 book2)

(defn one-two-or-more
  ([a] (println "One arg:" a))
  ([a b] (println "Deux args:" a b))
  ([a b & args] (println "More than two:" a b args)))
(one-two-or-more "Romain")
(one-two-or-more "Romain" "Robin")
(one-two-or-more "Romain"  "Robin" 2 3 4)