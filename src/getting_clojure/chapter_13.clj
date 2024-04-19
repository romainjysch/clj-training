(ns getting-clojure.chapter-13)

;; Chapter 13 : Records and Protocols

(defrecord FictionalCharacter [name appears-in author])

;(->FictionalCharacter)
;(map->FictionalCharacter)

(def harry (->FictionalCharacter "Harry Potter" "Harry Potter" "Rowling"))
(println harry)

(def ron (map->FictionalCharacter
           {:name "Ron Wisley"
            :appears-in "Harry Potter"
            :author "Rowling"}))
(println ron)
;; the map you pass in has keyword arguments that match the names of the fields in the record

(:author harry)
(:appears-in ron)

(count harry)
(keys ron)
(vals ron)

(def specific-harry (assoc harry :volumes "1-7"))
(println specific-harry)

;; the extras don’t affect the record type in any way
;; and they don’t get the magic speed boost of the built-in fields

(def irene {:name "Irene Adler"
            :appears-in "A Scandal in Bohemia"
            :author "Doyle"})

(:name harry) ; faster
(:name irene)

(defrecord SuperComputer [cpu n-cpus storage-gb])

(def watson-1 (->FictionalCharacter "John Watson" "Sign of the Four" "Doyle"))
(def watson-2 (->SuperComputer "Power7" 2880 4000))

(class watson-1)
(class watson-2)

(instance? FictionalCharacter watson-1)
(instance? FictionalCharacter watson-2)
(instance? SuperComputer watson-1)
(instance? SuperComputer watson-2)

;; protocols instead of instance? and class

(defrecord Employee [first-name last-name department])
(def alice (->Employee "Alice" "Smith" "IT"))

(defprotocol Person
  (full-name [this])
  (greeting [this msg])
  (description [this]))

(defrecord FictionalCharacter[name appears-in author]
  Person
  (full-name [this] (:name this))
  (greeting [this msg] (str msg " " (:name this)))
  (description [this] (str (:name this) " is a character in " (:appears-in this))))
(defrecord Employee [first-name last-name department]
  Person
  (full-name [this] (str first-name " " last-name))
  (greeting [this msg] (str msg " " (:first-name this)))
  (description [this] (str (:first-name this) " works in " (:department this))))

(def sofia (->Employee "Sofia" "Diego" "Finance"))
(def sam (->FictionalCharacter "Sam Weller" "The Pickwick Papers" "Dickens"))

(full-name sofia)
(description sam)
(greeting sofia "Hello!")
(greeting sam "Hello!")
(:author sam)

(defprotocol Marketable
  (make-slogan [this]))


(extend-protocol Marketable
  Employee
  (make-slogan [e] (str (:first-name e) " is the BEST employee!")) FictionalCharacter
  (make-slogan [fc] (str (:name fc) " is the GREATEST character!")) SuperComputer
  (make-slogan [sc] (str "This computer has " (:no-cpus sc) " CPUs!")))

(make-slogan sofia)
(make-slogan sam)
(make-slogan watson-2)

(extend-protocol Marketable
  String
  (make-slogan [s] (str \" s \" " is a string! WOW!")) Boolean
  (make-slogan [b] (str b " is one of the two surviving Booleans!")))

(make-slogan "Romain")
(make-slogan false)

(def romain (->Employee "R" "J" "IT"))
(full-name romain)
(greeting romain "yo")
(:department romain)

(defprotocol Product
  (inventory-name [this])
  (description [this]))
;; warning related to the description of protocol Person
;; when in doubt, put each protocol in its own namespace