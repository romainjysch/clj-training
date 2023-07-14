(ns exercism.lucian-luscious-lasagna)

;; Lucian's Luscious Lasagna
;; https://exercism.org/tracks/clojure/exercises/lucians-luscious-lasagna

(def expected-time 40)

(defn remaining-time
  "Takes the actual time in minutes the lasagna has been in the oven,
   and returns how many minutes the lasagna still has to remain in the oven."
  [actual-time]
  (- expected-time actual-time))
(remaining-time 10)


(defn prep-time
  "Takes the number of layers added to the lasagna,
   and returns how many minutes you spent preparing the lasagna"
  [num-layers]
  (* 2 num-layers))
(prep-time 3)


(defn total-time
  "Takes the number of layers of lasagna and the actual time in minutes it has been in the oven.
   Returns how many minutes in total you've worked on cooking the lasagna"
  [num-layers actual-time]
  (+ (prep-time num-layers) actual-time))
(total-time 3 10)
