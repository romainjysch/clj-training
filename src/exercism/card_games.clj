(ns exercism.card-games)

(defn rounds
  "Takes the current round number and returns
   a `list` with that round and the _next two_."
  [n]
  (let [current-round n
        round+1 (+ 1 n)
        round+2 (+ 2 n)]
    (list current-round round+1 round+2)))


(defn concat-rounds
  "Takes two lists and returns a single `list`
   consisting of all the rounds in the first `list`,
   followed by all the rounds in the second `list`"
  [l1 l2]
  (concat l1 l2))


(defn contains-round?
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
  (contains? (set l) n))


(defn card-average
  "Returns the average value of a hand"
  [hand]
  (let [count (count hand)
        total (reduce + hand)]
    (double (/ total count))))


(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
  (let [average (card-average hand)
        first-last-average (double (/ (+ (first hand) (last hand)) 2))
        median-pos (quot (count hand) 2)
        median-value (double (nth hand median-pos))]
    (println "average:" average (type average))
    (println "first-last-average:" first-last-average (type first-last-average))
    (println "median:" median-value (type median-value))
    (or (= average first-last-average)
        (= average median-value))))


(defn average-even-odd?
  "Returns true if the average of the cards at even indexes
   is the same as the average of the cards at odd indexes."
  [hand]
  (= (card-average (take-nth 2 hand))
     (card-average (take-nth 2 (rest hand)))))


(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
  (let [last (last hand)
        butlast (butlast hand)]
    (if (= last 11)
      (concat (butlast hand) [(* last 2)])
      hand)))


(comment
  :start
  (rounds 27)
  (concat-rounds '(27 28 29) '(35 36))
  (contains? [1 2 3 4] 3)
  (contains-round? '(27 28 29 35 36) 29)
  (contains-round? '(27 28 29 35 36) 30)
  (card-average '(5 6 7))
  (approx-average? '(1 2 3))
  (approx-average? '(0 1 5))
  (approx-average? '(2 3 4 8 8))
  (approx-average? '(1 2 3 5 9))
  (average-even-odd? '(1 2 3))
  (average-even-odd? '(1 2 3 4))
  (average-even-odd? '(1 3 5 7))
  (maybe-double-last '(5 9 11))
  (maybe-double-last '(5 9 10))
  :end)
