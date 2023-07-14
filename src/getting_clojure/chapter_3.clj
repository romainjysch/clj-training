(ns getting-clojure.chapter-3)

;; Chapter 3 : Maps, Keywords and Sets

(def dune {:title "Dune"
           :author "Franck Herbert"
           :published "1965"})
(dune :title)
(:author dune)

(assoc dune :pages "896")
(dissoc dune :published :isbn)

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

(def new-romain
  (-> romain
    (assoc :prenom "ROMAIN")
    (assoc :sport "Running")
    (update :age inc)
    (update-in [:bulletin :notes] conj 6)))
(println new-romain)

(def movies #{"Harry Potter" "Dune" "Star Wars"})
(conj movies "Mission Impossible")
(disj movies "Dune")

(def people #{"Arnaud" "Robin" "Victor"})
(if (contains? people "Romain")
  (println "Bonjour Romain")
  (println "Bonjour inconnu"))

(contains? people "Robin")
(contains? people :title)