(ns getting-clojure.chapter-12)

;; Chapter 12 : Destructuring

(def artists [:monet :austen])

(let [painter (first artists)
      novelist (second artists)]
  (println "The painter :" painter
           "The novelist :" novelist))

(let [[painter novelist] artists]
  (println "Painter :" painter)
  (println "Novelist :" novelist))

(def order [:first :second :third :fourth])

(let [[first second third fourth] order]
  (println "1st :" first)
  (println "2nd :" second)
  (println "3rd :" third)
  (println "4th :" fourth))

(let [[first second third] order]
  (println "1st :" first)
  (println "2nd :" second)
  (println "3rd :" third))

(let [[_ _ third fourth] order]
  (println "3rd :" third)
  (println "4th :" fourth))

(type (interleave "romain"))

(let [[c1 _ c3] "RJH"]
  (println "c1:" c1)
  (println "c3:" c3))

;; if you can turn it into a sequence, you can destructure it

(defn artist-description [[novelist poet]]
  (str "The novelist is " novelist " and the poet is " poet))
(artist-description [:austen :dickinson])

(def artist-map {:painter :monest
                 :novelist :austen})

(let [{painter :painter writer :novelist} artist-map]
  (println painter)
  (println writer))
;; the order of the symbol/key pairs is not important

(def austen {:name "Jane Austen"
             :parents {:father "George" :mother "Cassandra"}
             :dates {:born 1775 :died  1817}})

(let [{{dad :father mom :mother} :parents} austen]
  (println mom)
  (println dad))
;; I want to destructure austen, take :parents, associate :mother value to mom

(let [{name :name {dad :father} :parents {born :born} :dates} austen]
  (println "Name:" name)
  (println "Father:" dad)
  (println "Born:" born))

(def author {:name "Jane Austen"
             :books [{:title "Sense and Sensibility" :published 1811}
                     {:title "Emma" :published 1815}]})

(let [name :name {[_ emma] :books} author]
  (println name)
  (println (:published emma)))

(def authors [{:name "Jane Austen" :born 1775}
              {:name "Charles Dickens" :born 1812}])

(let [[a1 a2] authors]
  (println (:born a1))
  (println (:born a2)))

(let [[{a1 :born} {a2 :born}] authors]
  (println a1)
  (println a2))

(def romain {:name "Romain" :age 25 :gender :male})

(let [{:keys [name age gender]} romain]
  (println "Name:" name)
  (println "Age:" age)
  (println "Gender:" gender))

(defn character-desc [{:keys [name age gender]}]
  (println name)
  (println age)
  (println gender))

(character-desc romain)

(defn add-greeting [{:keys [name age] :as character}]
  (assoc character :greeting (str "My name is " name " and I'm " age)))

(add-greeting romain)