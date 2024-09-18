(ns getting-clojure.chapter_15
  (:require [clojure.spec.alpha :as s]))

;; Chapter 15 : Spec

(def getting-clojure {:title "Getting Clojure" :author "Olsen" :copies 1000})

(defn book?
  [x]
  (and
    (map? x)
    (string? (:title x))
    (string? (:author x))
    (pos-int? (:copies x))))
(book? getting-clojure)

(s/valid? number? 44) ; predicate and a value
(s/valid? number? "hello")
(s/valid? book? getting-clojure)

(def nb-gt-10-lt-100
  (s/and
    number?
    #(> % 10)
    #(< % 100)))

(s/valid? nb-gt-10-lt-100 9)
(s/valid? nb-gt-10-lt-100 10)
(s/valid? nb-gt-10-lt-100 11)
(s/valid? nb-gt-10-lt-100 100)

(def n-or-s
  (s/or
    :a-number number?
    :a-string string?
    :a-keyword keyword?))
;; or requires keywords

(s/valid? n-or-s 1)
(s/valid? n-or-s "hello")
(s/valid? n-or-s :hello)

(def n-gt-10-lt-100-or-s
  (s/or
    :n-gt-10-lt-100 nb-gt-10-lt-100
    :a-symbol symbol?))

(s/valid? n-gt-10-lt-100-or-s 10)
(s/valid? n-gt-10-lt-100-or-s 11)

(def coll-of-stings (s/coll-of string?))
(def coll-of-n-or-s (s/coll-of n-or-s))

(s/valid? coll-of-stings ["Romain" "Victor"])
(s/valid? coll-of-stings ["Romain" "Victor" :hello])
(s/valid? coll-of-n-or-s ["Romain" "Victor"])
(s/valid? coll-of-n-or-s ["Romain" "Victor" 12])

(def s-n-s-n (s/cat :s1 string? :s2 number? :s3 string? :s4 number?))
;; cat requires keywords

(s/valid? s-n-s-n ["Romain" 12 "Victor" 11])
(s/valid? s-n-s-n ["Romain" 12 "Victor" "Romain"])

(def book-s
  (s/keys :req-un [::title ; namespace-qualified keyword
                   ::author
                   ::copies]))

(s/valid? book-s {:title "Dune" :author "Herbert" :copies 100})
(s/valid? book-s {:title "Dune" :copies 100 :author "Herbert"})
(s/valid? book-s {:title "Dune" :copies 100 :author "Herbert" :year 2020}) ; also true
(s/valid? book-s {:title "Dune" :author "Herbert"})

(s/def ::book (s/keys :req-un [::title ::author ::copies]))

(s/valid? ::book {:title "Dune" :author "Herbert" :copies 100})
(s/valid? ::book {:title "Dune" :author "Herbert" :copies 100 :year 2020})
(s/valid? ::book {:title "Dune" :author "Herbert"})

(s/def ::title string?)
(s/def ::author string?)
(s/def ::copies pos-int?)
(s/def ::book (s/keys :req-un [::title ::author ::copies]))

(s/valid? ::book {:title "Dune" :author "Herbert" :copies 100}) ; boolean
(s/valid? ::book {:title "Dune" :author "Herbert" :copies :hello})
(s/valid? ::book {:title "Dune" :author "Herbert" :copies 100 :year 2020})
(s/valid? ::book {:title "Dune" :author "Herbert"})

(s/explain ::book {:title "Dune" :author "Herbert" :copies 100}) ; explaination - nil
(s/explain ::book {:title "Dune" :author "Herbert" :copies :hello})
(s/explain nb-gt-10-lt-100 1)

(s/conform nb-gt-10-lt-100 1) ; returns :clojure.spec.alpha/invalid
(s/conform ::book {:title "Dune" :author "Herbert" :copies 100})
(s/conform number? 1944) ; returns 1944, returns the result
(s/conform number? "hello")

(s/def ::inventory
  (s/coll-of ::book))

(defn find-by-title
  [title inventory]
  {:pre [s/valid? ::title title
         s/valid? ::inventory inventory]}
  (some #(when (= (:title %) title) %) inventory))

;; spec for the find-by-title function
(s/fdef find-by-title
        :args (s/cat :title ::title
                     :inventory ::inventory))