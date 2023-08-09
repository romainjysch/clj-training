(ns getting-clojure.chapter-10)

;; Chapter 10 : Sequences

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

(seq [])
(seq '())
(seq {})

(first seq-list-title)
(first seq-vec-title)
(rest seq-vec-title)
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
