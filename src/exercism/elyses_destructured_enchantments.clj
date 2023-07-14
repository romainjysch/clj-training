(ns exercism.elyses-destructured-enchantments)

;; Elyses Destructured Enchantments
;; https://exercism.org/tracks/clojure/exercises/elyses-destructured-enchantments

(def deck [5 4 7 10])

;;

(defn first-card
  "Returns the first card from deck."
  [deck]
  (first deck))

(first-card deck)

;;

(defn second-card
  "Returns the second card from deck."
  [deck]
  (second deck))

(def empty-deck [])

(second-card empty-deck)
(second-card deck)

;;

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [first (first-card deck)
        second (second-card deck)
        rest (drop 2 deck)]
    (vec (cons second (cons first rest)))))

(vec (drop 2 deck))

(swap-top-two-cards deck)

;;

(defn discard-top-card
  "Returns a sequence containing the first card and
   a sequence of the remaining cards in the deck."
  [deck]
  (let [first (first deck)
        rest (if (empty? (rest deck)) nil (vec (rest deck)))]
    [first rest]))

(discard-top-card [7])
(discard-top-card deck)

;;

(def face-cards
  ["jack" "queen" "king"])

(vec (concat face-cards ["1" "2"] ["3"]))
(set (concat #{1 2} #{2 4}))

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (let [[start rest] (split-at 1 deck)]
    (vec (concat start face-cards rest))))


(insert-face-cards deck)