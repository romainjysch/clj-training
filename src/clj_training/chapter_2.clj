(ns clj-training.chapter-2)

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
(conj people-vector "Marion")

(def another-vector [1 [2 3 4] 5])
(conj another-vector [6 7])

(def people-list '("Robin" "Victor" "Romain"))
(conj people-list "Marion")

(defn third-element [elements]
  ;(nth elements 2)
  (elements 2))
(third-element people-vector)

(def last-week [0 2 5 3 7 8 4])
(vec (map inc last-week))

(def numbers [1 2 3 4])
(type numbers)
(println (vec (butlast numbers)))