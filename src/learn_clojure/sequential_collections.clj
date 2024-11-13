(ns learn-clojure.sequential-collections)

[1 2 3]

;; indexed access
(get ["abc" false 99] 0)
(get ["abc" false 99] 1)
(get ["abc" false 99] 14) ; nil

;; count
(count [1 2 3])
(count {:a 1 :b 2})

;; constructing
(vector 1 2 3)

;; adding elements
(conj [1 2] 3 4) ; [1 2 3 4]

;; immutability
(def v [1 2 3])
(conj v 4 5 6)

;; lists
(def cards '(10 :ace :jack 9))

(first cards)
(second cards)
(rest cards) ; lists are not indexed

;; adding elements
;; conj add where it can be done in constant time for the data structure
(conj cards :queen)

;; stack access
(def stack '(:a :b :c :d))
(peek stack)
(pop stack)