(ns exercism.tracks-on-tracks-on-tracks)

;; Tracks on Tracks on Tracks
;; https://exercism.org/tracks/clojure/exercises/tracks-on-tracks-on-tracks

(defn new-list
  "Creates an empty list of languages to practice."
  []
  (list))
(new-list)

;;

(def languages (new-list))

(defn add-language
  "Adds a language to the list."
  [lang-list lang]
  (conj lang-list lang))
(add-language languages "Java")

;;

(def lisps '("Clojure" "Common Lisp" "Scheme"))

(defn first-language
  "Returns the first language on the list."
  [lang-list]
  (first lang-list))
(first-language lisps)

;;

(defn remove-language
  "Removes the first language added to the list."
  [lang-list]
  (rest lang-list))
(remove-language lisps)

;;

(defn count-languages
  "Returns the total number of languages on the list."
  [lang-list]
  (count lang-list))
(count-languages lisps)

;;

(defn learning-list []
  "Creates an empty list, adds Clojure and Lisp, removes Lisp, adds
  Java and JavaScript, then finally returns a count of the total number
  of languages."
  (let [list (new-list)]
    (-> list
        (add-language "Clojure")
        (add-language "Lisp")
        (remove-language)
        (add-language "Java")
        (add-language "JavaScript")
        (count-languages))))
(learning-list)