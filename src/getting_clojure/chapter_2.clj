(ns getting-clojure.chapter-2)

;; Chapter 2 : Vectors and Lists

(def people-vector ["Robin" "Victor" "Romain"])
(count people-vector)
(first people-vector)
(last people-vector)
(vec (rest people-vector))
(nth people-vector 1)
(nthnext people-vector 2)
(nthrest people-vector 1)
(people-vector 2) ; /!\ index !
(conj people-vector "Marion") ; at the end
(cons "Arnaud" people-vector) ; at the start, in a sequence
(vec (cons "Arnaud" people-vector)) ; in a vector

(def another-vector [1 [2 3 4] 5])
(conj another-vector [6 7])

(def people-list '("Robin" "Victor" "Romain"))
(conj people-list "Marion")

(defn third-element [coll]
  ;(nth elements 2)
  (coll 2))
(third-element people-vector)

(def last-week [0 2 5 3 7 8 4])
(vec (map inc last-week))

(map #(* % 2) last-week)

(def numbers [1 2 3 4])
(type numbers)
(vec (butlast numbers))
(last numbers)

(def simple-vector [1 2 3])
(def any-clojure-value [1 true ["three" 4] "five" false]) ; embed any value
(println any-clojure-value)
(def using-vector-function (vector 1 2 3))
(println using-vector-function)

(rest simple-vector) ; anything without first element in a sequence
(rest (rest simple-vector)) ; anything with first two elements
(rest ["Clojure"]) ; return empty collection
(rest []) ; same
(nth simple-vector 2) ; 0-based index
(simple-vector 2) ; same

(def new-simple-vector (conj simple-vector 4)) ; simple-vector stay the same !
(println new-simple-vector)
simple-vector

(def simple-list '(1 2 3 4))
(println simple-list)
(def empty-list-v1 '())
empty-list-v1
(println empty-list-v1)
(def empty-list-v2 ())
(println empty-list-v2)

;; vector is like an array; easier when adding at the end
;; list is like a linked-list; easier when adding at the front

(def languages-list '("Clojure" "Java"))
(type languages-list)
(conj languages-list "Rust") ; with a list, add at the start
(def languages-vector ["Clojure" "Java"])
(conj languages-vector "Rust") ; with a vector, add at the end

;; data structures store their data in chunks, organized in a shallow tree
;; immutable data structures = persistent data structures