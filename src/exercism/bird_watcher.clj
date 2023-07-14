(ns exercism.bird_watcher)

;; Bird Watcher
;; https://exercism.org/tracks/clojure/exercises/bird-watcher

(def last-week [0 2 5 3 7 8 4])

(last last-week)
(first last-week)

;;

(defn today [birds]
  (last birds))

(defn yesterday [birds]
  (last (butlast birds)))

(today last-week)
(yesterday last-week)

;;

(defn inc-bird [birds]
  (conj (vec (butlast birds))
        (inc (last birds))))
(inc-bird last-week)

;;



(def week-without-0-bird [1 1 1 1 1 1 1])

(defn day-without-birds? [birds]
  (if (some (fn [x] (= x 0)) birds)
    true
    false))

(day-without-birds? week-without-0-bird)
(day-without-birds? last-week)

;;

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(n-days-count last-week 4)

;;

(defn busy-days [birds]
  (count (filter (fn [x] (>= x 5)) birds)))

(busy-days last-week)

;;

(def pattern [1 0 1 0 1 0 1])

(defn odd-week? [birds]
  (if (= birds [1 0 1 0 1 0 1])
    true
    false))

(odd-week? pattern)
(odd-week? last-week)