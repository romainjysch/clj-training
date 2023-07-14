(ns exercism.log-levels
  (:require [clojure.string :as str]))

;; Log Levels
;; https://exercism.org/tracks/clojure/exercises/log-levels

(def log "[ERROR]: Invalid operation  ")

;;

(defn message [s]
  (-> s
      (str/replace #".*]: " "")
      (str/trim)))

(message log)

;;

(defn log-level [s]
  "Takes a string representing a log line
   and returns its level in lower-case."
  (-> s
      (str/replace #":.*" "")
      (str/replace #"\[|\]" "")
      (str/lower-case)
      (str/trim)))

(log-level log)

;;

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (str (message s) " (" (log-level s) ")"))

(reformat log)