(ns getting-clojure.chapter-3)

;; Chapter 3 : Maps, Keywords and Sets

{"firstname" "Romain" "age" 24}
{:firstname "Romain" :age 25}

(hash-map "firstname" "Romain"
          "age" 24
          "city" "Lausanne") ; (hash-map) and not (map) /!\

(hash-map :firstname "Romain" :age 25 :birthday "April")

(def dune {"title" "Dune"
           "author" "Herbert"
           "published" "1965"})
(get dune "title")
(get dune "year" "oups?") ; show "oups?" if not found
(dune "author")
(dune "year" "oups?") ; show "oups?" as well

(def boolean-map {true "true"
                  false "false"})

(boolean-map true)
(boolean-map false)

;; everything can be a key, but :keyword is better as a label

(def dune {:title "Dune"
           :author "Franck Herbert"
           :published "1965"})
(dune :title)
(:author dune)
(:title dune) ; keyword first is the most common way
(println "Title:" (dune :title))

(def dune-with-pages
  (assoc dune :pages 896
              :isbn "bla-bla")) ; cause we can't modify the first map
(println dune-with-pages)
(:pages dune-with-pages)
(:isbn dune-with-pages)

(assoc dune :pages 896)
(dissoc dune :published :isbn) ; nothing is done with isbn, quietly ignored

(def dune-without-author
  (dissoc dune :author))
(println dune-without-author)

;; assoc and dissoc work fine on vectors

(keys dune-with-pages) ; get hold of all the keys in a map

(def me
  (sorted-map :firstname "Romain"
              :age 24
              :sport "Running")) ; keys in order
(println me)
(keys me)
(vals me) ; get all the values out of the map
(select-keys me [:age :sport])
(assoc me "firstname" "Romain") ; can't be done
(assoc me "l" "Jy") ; can't be done

(keys {:f "Romain" "l" "Jy"})

(def map-of-map {:1 {:firstname "Romain"
                     :age 24
                     :city "Lausanne"}
                 :2 {:firstname "Robin"
                     :age 24
                     :city "Fribourg"}
                 :3 {:firstname "Victor"
                     :age 26
                     :city "Neuchâtel"}})
(:1 map-of-map)
(select-keys map-of-map [:1 :2])

(def map-of-map-v2 (hash-map :1 {:firstname "Romain"
                                 :age 24
                                 :city "Lausanne"}
                            :2 {:firstname "Robin"
                                :age 24
                                :city "Fribourg"}
                            :3 {:firstname "Victor"
                                :age 26
                                :city "Neuchâtel"}))
(select-keys map-of-map-v2 [:1 :2])

(defn print-book [book]
  (println "Title:" (book :title))
  (println "Author:" (book :author))
  (println "Published:" (book :published))
  (println "ISBN:" (book :isbn)))
(print-book dune)

(def romain {:prenom "Romain"
             :nom "Jysch"
             :age 24
             :ville "Lausanne"
             :bulletin {:notes [5.5 5 4.5]}})
(println romain)
(:notes romain) ; nil
(:bulletin romain) ; {:notes [5.5 5 4.5]}
(get-in romain [:bulletin :notes])

(def new-romain
  (-> romain
    (assoc :prenom "ROMAIN")
    (assoc :sport "Running")
    (update :age inc)
    (update-in [:bulletin :notes] conj 6)))
(println new-romain)

(-> me
    (assoc :city "Lausanne") ; and sorted correctly
    (dissoc :sport)
    (update :age inc))

(contains? me :firstname)
(first me) ; returns [:age 24]
(rest me) ; returns ([:firstname "Romain"] [:sport "Running"])
(count me) ; returns 3

;; use commas if they help

(set ["Romain" "Robin"]) ; create a set based on a collection
#{"Romain" "Robin"} ; same
#{"Romain" "Robin" "Robin"} ; duplicate error !
#{:multiple :keywords :set}
#{:multiple :keywords "Test" true} ; everything

(def my-set #{:multiple :keywords :set})
(println my-set)
(contains? my-set :keywords)
(:multiple my-set) ; same as map, :keyword can be a function

(def movies #{"Harry Potter" "Dune" "Star Wars"})
(conj movies "Mission Impossible")
(disj movies "Dune") ; based on first movies set

(def people #{"Arnaud" "Robin" "Victor"})
(if (contains? people "Romain")
  (println "Bonjour Romain")
  (println "Bonjour inconnu"))
(def more-people (conj people "Romain")) ; make a second and bigger set
(more-people "Romain")
(def less-people (disj people "Victor")) ; make a second and smaller set
(less-people "Victor")

(contains? people "Robin")
(contains? people :title)
(people "Robin")

;; sets are not common

(def subprotocol "h2")
(#{"derby" "h2" "hsqldb" "sqlite"} subprotocol) ; return "h2"