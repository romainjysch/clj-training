(ns getting-clojure.chapter-10)

;; Chapter 10 : Sequences

(count [1 2 "trois"])
(count {:un 1 :deux 2 :trois 3})

(def vec-title ["Dune" "Star Wars" "Mission Impossible"])
(println vec-title)
(def seq-vec-title (seq vec-title))
(println seq-vec-title)
(def list-title '("Dune" "Star Wars"))
(println list-title)
(def seq-list-title (seq list-title))
(println seq-list-title)
(def map-title {:title "Dune" :author "Herbert"})
(println map-title)
(def seq-map-title (seq map-title))
(println seq-map-title)

(seq []) ; nil
(seq '())
(seq {})

(first seq-list-title)
(first seq-vec-title)
(rest seq-vec-title) ; empty sequence if empty
(next seq-vec-title) ; nil if empty
(cons "Mario" seq-vec-title)

(defn flavor [x]
  (cond
    (list? x) :list
    (vector? x) :vector
    (set? x) :set
    (map? x) :map
    (string? x) :string
    :else :unknown))

(defmulti my-count flavor)

(defn my-count [col]
  (let [the-seq (seq col)]
    (loop [n 0 s the-seq]
      (if (seq s)
        (recur (inc n) (rest s))
        n))))

(my-count vec-title)
(my-count seq-vec-title)

(type (rest [1 2 3]))
(type (rest {:fname "Romain" :lname "Z"}))
(type (rest {:fname "Romain" :lname "Z"}))
(type (cons 0 [1 2 3]))

(rest [1 2 3]) ; returns a seq
(next [1 2 3]) ; returns a seq

(sort vec-title)
(reverse vec-title)
(reverse (sort vec-title))
(partition 1 vec-title)

(def titles ["Dune" "Mission Impossible"])
(def authors '("Herbert" "Clarke"))
(interleave titles authors)
(interpose "and" (interleave titles authors))

(filter neg? [-1 2 -55 -3453 45 3])

(def books
  [{:title "Deep Six" :price 13.99 :genre :sci-fi :rating 6}
   {:title "Dracula" :price 1.99 :genre :horror :rating 7}
   {:title "Emma" :price 7.99 :genre :comedy :rating 9}
   {:title "2001" :price 10.50 :genre :sci-fi :rating 5}])

(defn cheap?
  [book]
  (when (< 10 (:price book))
    book))

(filter cheap? books) ; filter returns possibliy an empty sequence
(some cheap? books) ; some returns the first true element or nil

(def some-numbers [1, 53, 811])

(defn doubled
  [numbers]
  (map #(* 2 %) numbers))
(doubled some-numbers)


(map (fn [book] (:title book)) books)
(map :title books)
(map (fn [book] (count (:title book))) books)
(map (comp count :title) books)
(for [b books]
  (count (:title b)))

(def numbers [10 20 30 40 50])
(reduce + numbers)
(reduce + 100 numbers)

(defn higher-price [hi book]
  (let [price (:price book)]
    (if (> price hi)
      price
      hi)))
(reduce higher-price 0 books)

(defn format-top-titles
  [books]
  (apply
    str
    (interpose " // " (map :title (take 3 (reverse (sort-by :rating books)))))))
(format-top-titles books)

(apply str (interpose " // " (map :title (take 3 (reverse (sort-by :rating books))))))

(defn format-top-titles-v2
  [books]
  (->>
    books
    (sort-by :rating)
    reverse
    (take 3)
    (map :title)
    (interpose " // ")
    (apply str)))
(format-top-titles-v2 books)

(zipmap [:un :deux :trois] [1 2 3])

(butlast (zipmap [:un :deux :trois] [1 2 3]))

;; A *vector* ending with "Jaws".
(conj ["Emma" "1984" "The Maze Runner"] "Jaws")

;; A *list* starting with "Jaws".
(conj '("Emma" "1984" "The Maze Runner") "Jaws")

;; A *seq* starting with "Jaws".
(cons "Jaws" ["Emma" "1984" "The Maze Runner"])

;; A *seq* starting with "Jaws".
(cons "Jaws" '("Emma" "1984" "The Maze Runner"))

