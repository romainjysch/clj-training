(ns getting-clojure.chapter-6)

;; Chapter 6 : Functional Things

(def dune {:title "Dune"
           :author "Herbert"
           :price 5
           :genre :sf})
(update dune :price inc)

(def getting-clojure {:title "Getting Clojure"
                      :author "Olsen"
                      :price 30
                      :genre :programming})

(defn cheap? [book]
  (when (<= (book :price) 10)
    book))
(cheap? dune)
(cheap? getting-clojure)

(defn pricey? [book]
  (when (> (book :price) 10)
    book))
(pricey? dune)
(pricey? getting-clojure)

(defn sf? [book]
  (when (= (book :genre) :sf)
    book))
(sf? dune)
(sf? getting-clojure)

(defn programming? [book]
  (when (= (book :genre) :programming)
    book))
(programming? dune)
(programming? getting-clojure)

(defn cheap-and-sf? [book]
  (when (and (cheap? book)
             (sf? book))
    book))
(cheap-and-sf? dune)
(cheap-and-sf? getting-clojure)

(defn pricey-and-programming [book]
  (when (and (pricey? book)
             (programming? book))
    book))
(pricey-and-programming dune)
(pricey-and-programming getting-clojure)

;; functions are values

cheap?
(def reasonably-priced? cheap?) ; bind a function value to another symbol
(reasonably-priced? dune)
(reasonably-priced? getting-clojure)

(defn run-with-dune [f] ; the function is always run with dune
  (f dune)) ; like f.apply(dune) in Java
(run-with-dune cheap?) ; like (cheap? dune)
(run-with-dune pricey?) ; like (pricey? dune)

(defn both? [first-pred-f second-pred-f book] ; combining predicates
  (when (and (first-pred-f book)
             (second-pred-f book))
    book))
(both? cheap? sf? dune)
(both? pricey? sf? getting-clojure)
(both? pricey? programming? getting-clojure)

;; both? is better than the pricey-and-programming function

(println "A function:" (fn [x] (* 2 x)))
((fn [x] (* 2 x)) 4) ; like (squared 4) and not binded to a name
((fn [x] (* x x)) 4)
(def double (fn [x] (* 2 x)))
(def squared (fn [x] (* x x))) ; now binded to a name
(double 4)
(squared 4)

;; nameless functions :
((fn [book]
   (when (<= (book :price) 10)
     book)) dune)
((fn [book]
   (when (<= (book :price) 10)
     book)) getting-clojure)

;; function that produce functions
(defn cheaper-f [max-price]
  (fn [book]
    (when (<= (book :price) max-price)
      book)))
;; create functions with the function who produce functions
(def extremely-cheap? (cheaper-f 1.00))
(def very-cheap? (cheaper-f 3.00))
(def a-bit-cheap? (cheaper-f 10.00))
(extremely-cheap? dune)
(very-cheap? dune)
(a-bit-cheap? dune)

(defn more-expensive-f [min-price]
  (fn [book]
    (when (> (book :price) min-price)
      book)))
(def expensive? (more-expensive-f 20))
(expensive? getting-clojure)
(def very-expensive? (more-expensive-f 100))
(very-expensive? getting-clojure)

(defn both-f [first-pred-f second-pref-f]
  (fn [book]
    (when (and (first-pred-f book)
               (second-pref-f book))
      book)))
(def cheap-sf? (both-f cheap? sf?))
(def very-cheap-sf? (both-f very-cheap? sf?))
(cheap-sf? dune)
(very-cheap-sf? dune)
(cheap-sf? getting-clojure)

(def cheap-sf-dune? ; another level of meta
  (both-f
    cheap-sf?
    (fn [book] (= (book :title) "Dune"))))
(cheap-sf-dune? dune)

;; closure is when we remember the bindings when the function was born

(+ 1 2 3 4)
(def sum +)
(println sum)
(def args [1 2 3 4])
(apply sum args) ; apply f on [coll]

(def v ["Hello, " "I'm " 24])
(apply str v) ; apply the str function on the v coll
(apply list v)
(apply vector (apply list v))

(inc 1)
(defn my-inc [x]
  (+ 1 x))
(my-inc 1)

(def my-inc-v2 (partial + 1)) ; partial fills the argument for an existing function
(my-inc-v2 2) ; here partial fills 1 to the + function

(def add2 (partial + 2)) ; 2 first argument, still need another one
(add2 5)

(def multiply-by-5 (partial * 5))
(multiply-by-5 5)

(defn add [x y]
  (+ x y))
(add 5 5)
(def add-v2 (partial add 5))
(add-v2 5)

(defn cheaper-than [max-price book]
  (when (<= (book :price) max-price)
    book))
;; Partial takes a first argument that we already know
;; def and not defn because of partial
(def real-cheap? (partial cheaper-than 1.00)) ; still need the book argument
(def kind-of-cheap? (partial cheaper-than 5.00))
(def cheap-v2? (partial cheaper-than 10.00))
(real-cheap? dune) ; here is the book
(kind-of-cheap? dune)

(defn not-sf? [book]
  (not (sf? book)))
(not-sf? dune)
(not-sf? getting-clojure)

;; def and not defn because of complement
(def not-sf-v2? (complement sf?)) ; complement = wrap the function with a not
(not-sf-v2? dune)
(not-sf-v2? getting-clojure)

;; def and not defn because of every-pred
(def cheap-sf-v2? (every-pred cheap? sf?)) ; every predicate has to be true
(def cheap-sf-dune-v2 (every-pred
                        cheap?
                        sf?
                        (fn [book] (= (book :title) "Dune"))))
(cheap-sf-v2? dune)
(cheap-sf-dune-v2 dune)

(def double (partial * 2))
(double 4)

;; function literal :
(#(* 2 %) 3) ; is like ((fn [x] (* 2 x)) 3)
(#(+ %1 %2 %3) 1 1 1) ; #(+ %1 %2 %3) = f, 1 1 1 = args

(def double-literal #(* 2 %))
(double-literal 3)

(def harry-potter {:title "HP" :copies 1000})
(def harry-potter-v2 (update harry-potter :copies inc))
(println harry-potter-v2)

(def author {:name "Rowling"
             :book {:title "HP" :copies 1000}})
(update-in author [:book :copies] inc)
(assoc-in author [:book :pages] 300)

(-> author
    (update-in [:book :copies] inc)
    (assoc-in [:book :pages] 300))

;; defn is def + fn