(ns getting-clojure.chapter-11)

;; Chapter 11 : Lazy Sequences

(def jack "All work and no play makes Jack a dull boy.")
(def text [jack jack jack jack jack jack jack jack jack jack])
jack
text

;; sequence with the value supplied
(def repeated-text (repeat jack))

(first repeated-text)
(nth repeated-text 10)
(nth repeated-text 10202)

(take 20 repeated-text)

;; cycle for collections
(take 7 (cycle [1 2 3 4]))

;; iterate with a function and a starting value
(def numbers (iterate inc 1))

(first numbers)
(nth numbers 0)
(nth numbers 1)
(nth numbers 2)
(nth numbers 99)
(take 5 numbers)
(second numbers)

(take 3 [1 2 3 4 5])

;; waits to be asked
(def many-nums (take 1000000 (iterate inc 1)))

(println (take 20 many-nums))

(time (println (take 9999 many-nums)))

(def evens (map #(* 2 %) (iterate inc 1)))
(take 20 evens)
(take 40 evens)

(take 10 (interleave numbers evens))

(def volumes (iterate inc 1))

;; map returns a lazy sequence
(def titles (map #(str "Dune, Book " %) volumes))
(take 3 titles)

(def first-names ["Bob" "Jane" "Chuck" "Leo"])
(def last-names ["Jordan" "Austen" "Dickens" "Tolstoy" "Poe"])

(defn combine-names
  [fname lname]
  (str fname " " lname))

(def authors
  (map combine-names
       (cycle first-names)
       (cycle last-names)))

(take 10 authors)

(defn make-book
  [title author]
  {:title title
   :author author})

(def test-books (map make-book titles authors))
(take 4 test-books)
(first test-books)

(seq [1 2 3])
(lazy-seq [1 2 3])

(defn chatty-vector
  []
  (println "Go!")
  [1 2 3])
(chatty-vector)

(def s (lazy-seq (chatty-vector)))
(first s)
(take 2 s)

(take 3 ["Romain" "Victor" "Arnaud" "Manon"])

(cons 4 (seq [1 2 3]))

;; repeat, cycle and itereate in order to create lazy sequences
;; map and take for processing lazy sequences