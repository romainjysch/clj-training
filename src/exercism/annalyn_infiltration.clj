(ns exercism.annalyn-infiltration)

;; Annalyn's Infiltration
;; https://exercism.org/tracks/clojure/exercises/annalyns-infiltration

(def knight-awake? false)
(def archer-awake? true)
(def prisoner-awake? true)
(def dog-present? false)

;;

(defn can-fast-attack?
  "Returns true if a fast-attack can be made, false otherwise."
  [knight-awake?]
  (not knight-awake?))

(can-fast-attack? knight-awake?)

;;

(defn can-spy?
  "Returns true if the kidnappers can be spied upon, false otherwise."
  [knight-awake? archer-awake? prisoner-awake?]
  (or knight-awake? archer-awake? prisoner-awake?))

(can-spy? knight-awake? archer-awake? prisoner-awake?)

;;

(defn can-signal-prisoner?
  "Returns true if the prisoner can be signalled, false otherwise."
  [archer-awake? prisoner-awake?]
  (and (not archer-awake?) prisoner-awake?))

(can-signal-prisoner? archer-awake? prisoner-awake?)

;;

(defn can-free-prisoner?
  "Returns true if prisoner can be freed, false otherwise."
  [knight-awake? archer-awake? prisoner-awake? dog-present?]
  (or
    (and
      dog-present?
      (not archer-awake?))
    (and
      (not dog-present?)
      (not knight-awake?)
      (not archer-awake?)
      prisoner-awake?)))

(can-free-prisoner? knight-awake? archer-awake? prisoner-awake? dog-present?)