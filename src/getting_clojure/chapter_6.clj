(ns getting-clojure.chapter-6)

;; Chapter 6 : Functional Things

(def dune {:title "Dune"
           :author "Herbert"
           :price 5
           :genre :sf})

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

cheap?
(def reasonably-priced? cheap?)
(reasonably-priced? dune)
(reasonably-priced? getting-clojure)

(defn run-with-dune [f]
  (f dune))
(run-with-dune cheap?)
(run-with-dune pricey?)

(defn both? [first-pred-f second-pred-f book]
  (when (and (first-pred-f book)
             (second-pred-f book))
    book))
(both? cheap? sf? dune)
(both? pricey? sf? getting-clojure)

((fn [x] (* 2 x)) 4)
(def double (fn [x] (* 2 x)))
(double 4)

((fn [book]
   (when (<= (book :price) 10)
     book)) dune)
((fn [book]
   (when (<= (book :price) 10)
     book)) getting-clojure)

(defn cheaper-f [max-price]
  (fn [book]
    (when (<= (book :price) max-price)
      book)))
(def extremely-cheap? (cheaper-f 1.00))
(def very-cheap? (cheaper-f 5.00))
(def a-bit-cheap? (cheaper-f 10.00))
(extremely-cheap? dune)
(very-cheap? dune)
(a-bit-cheap? dune)

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

(def cheap-sf-dune?
  (both-f
    cheap-sf?
    (fn [book] (= (book :title) "Dune"))))
(cheap-sf-dune? dune)

(+ 1 2 3 4)
(def args [1 2 3 4])
(apply + args) ; apply takes arguments

(def v ["Hello, " "I'm " 24])
(apply str v)
(apply list v)
(apply vector (apply list v))

(inc 1)
(defn my-inc [x]
  (+ 1 x))
(my-inc 1)

(def my-inc-v2 (partial + 1))
(my-inc-v2 1)

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
(def real-cheap? (partial cheaper-than 1.00))
(def kind-of-cheap? (partial cheaper-than 5.00))
(def cheap-v2? (partial cheaper-than 10.00))

(defn not-sf? [book]
  (not (sf? book)))
(not-sf? dune)
(not-sf? getting-clojure)

;; def and not defn because of complement
(def not-sf-v2? (complement sf?))
(not-sf-v2? dune)
(not-sf-v2? getting-clojure)

;; def and not defn because of every-pred
(def cheap-sf-v2? (every-pred cheap? sf?))
(def cheap-sf-dune-v2 (every-pred
                        cheap?
                        sf?
                        (fn [book] (= (book :title) "Dune"))))
(cheap-sf-v2? dune)
(cheap-sf-dune-v2 dune)

(def double (partial * 2))
(double 4)

(#(* 2 %) 3)
(#(+ %1 %2 %3) 1 1 1)

(def double-literal #(* 2 %))
(double-literal 3)

(def harry-potter {:title "HP" :copies 1000})
(def harry-potter-v2 (update harry-potter :copies inc))
(println harry-potter-v2)

(def author {:name "Rowling"
             :book {:title "HP" :copies 1000}})
(def author-v2 (update-in author [:book :copies] inc))
(println author-v2)

(def author-v3 (assoc-in author-v2 [:book :pages] 300))
(println author-v3)