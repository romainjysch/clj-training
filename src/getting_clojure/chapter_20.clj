(ns getting-clojure.chapter-20)

;; Chapter 20 : Macros

(defn print-rating [rating]
  (cond
    (pos-int? rating) (println "Good book!")
    (zero? rating) (println "Neutral.")
    :else (println "Bad...")))

(print-rating 1)
(print-rating 0)
(print-rating -1)

(defn arithmetic-if [n pos zero neg]
  (cond
    (pos? n) pos
    (zero? n) zero
    :else neg))

(arithmetic-if 0 :great :neutral :boring)

(defn print-rating [rating]
  (arithmetic-if rating
                 (println "Good book!")
                 (println "Totally indifferent.")
                 (println "Run away!")))

(print-rating 10)

(defn arithmetic-if [n pos-f zero-f neg-f]
  (cond
    (pos? n) (pos-f)
    (zero? n) (zero-f)
    :else (neg-f)))

(defn print-rating [rating]
  (arithmetic-if rating
                 #(println "Good book!")
                 #(println "Totally indifferent.")
                 #(println "Run away!")))

(print-rating 10)

(defmacro arithmetic-if [n pos zero neg]
  (list 'cond
        (list 'pos? n) pos
        (list 'zero? n) zero
        :else neg))

(arithmetic-if 10 :loved-it :neutral :hated-it)
(arithmetic-if 0 :loved-it :neutral :hated-it)
(arithmetic-if -10 :loved-it :neutral :hated-it)

(defmacro print-it [something]
  (list 'println "Something is" something))

(print-it (+ 1 2))
(println (+ 1 2))

'(:a :syntax "quoted" :list 1 2 3 4)

(def n 100)
(def pos "It's positive!")
(def zero "It's zero!")
(def neg "It's negative")


(defmacro arithmetic-if [n pos zero neg]
  `(cond
     (pos? ~n) ~pos
     (zero? ~n) ~zero
     :else ~neg))

(arithmetic-if 10 :loved-it :neutral :hated-it)
(arithmetic-if 0 :loved-it :neutral :hated-it)
(arithmetic-if -10 :loved-it :neutral :hated-it)