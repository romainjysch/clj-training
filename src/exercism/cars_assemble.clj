(ns exercism.cars-assemble)

;; Cars, Assemble!
;; https://exercism.org/tracks/clojure/exercises/cars-assemble

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (cond
    (= speed 0) (float 0)
    (and (<= speed 4) (>= speed 1)) (float (* speed 221))
    (and (<= speed 8) (>= speed 5)) (* speed 221 0.9)
    (= speed 9) (* speed 221 0.8)
    (= speed 10) (* speed 221 0.77)))

(production-rate 0)
(production-rate 6)

;;

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int (/ (production-rate speed) 60)))

(working-items 6)