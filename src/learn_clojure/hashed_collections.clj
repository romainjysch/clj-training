(ns learn-clojure.hashed-collections)

;; Sets
(def players #{"Alice", "Bob", "Romain"})
players
(conj players "Robin")
(disj players "Bob")
(contains? players "Romain")
(def sorted-players (sorted-set "Alice", "Bob", "Romain")) ; use comapator by default
sorted-players
(def new-players #{"Max", "Tim", "Greg"})
(into new-players sorted-players) ; not sorted
(into sorted-players new-players) ; sorted

;; Maps
(def scores {"Romain" 1000 "Bob" 999 "Alice" 1001})
scores
(assoc scores "Kelly" 2000)
(assoc scores "Romain" 0) ; replace the val
(dissoc scores "Romain")
(get scores "Romain")
(def directions {:north 0 :east 1 :south 2 :west 3}) ; used as a constant lookup table
(:north directions)
(directions :south)
(get scores "Sam") ; => nil
(get scores "Sam" 1) ; => 1
(directions :northwest) ; => nil
(:northwest directions -1) ; => -1
(contains? scores "Romain")
(find scores "Romain") ; => ["Romain" 1000]
(second (find scores "Romain"))
(keys scores)
(def new-scores {:romain 1000 :max 2000})
(keys new-scores)
(vals scores)
(vals new-scores)
(def people [:romain :robin :victor])
(def people-scores [1000 2000 3000])
(zipmap people people-scores)
(zipmap players (repeat 0))
(reduce + people-scores)
(def other-scores {"Angela" 1000 "Jeff" 900})
(merge scores other-scores); merge for maps only
(merge-with + scores other-scores)
(sorted-map :romain 10 :victor 20 :robin 30) ; keywords comparison