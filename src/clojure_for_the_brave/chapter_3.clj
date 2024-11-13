(ns clojure-for-the-brave.chapter-3)

(if 1
  (do (println "Romain")
      "Romain")
  (do (println "False")
      "False"))

(when (= 1 1)
  (println "Romain")
  "Romain")

(or false nil :romain :victor)
(or false nil)
(or false true)

(and :romain :victor)
; last truthy value

(and :romain nil :victor)
; first falsey value

(defn error-message
  [severity]
  (str "It's a disaster ! We're "
       (if (= severity :mild)
         "mildly inconvenienced"
         "doooomed")))
(error-message :mild)

(defn error-message-v2
  [severity]
  (str "We're "
       (case severity
         :mild "mildly"
         :high "doomed"
         "chill")))
(error-message-v2 :mild)
(error-message-v2 :high)
(error-message-v2 :info)

(get {:a 1 :b 2} :b)
(get {:a 1 :b {:c "hum"}} :b)

((get {:a + :b -} :a) 1 1)
(:first {:first "r" :last "j"})

(nth [3 2 1] 0)

(first [1 2 3])

((first [+ 0]) 1 2 3)

(inc 1.1)
(map #(* 2 %) [2 3 4])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println lat)
  (println lng))
(announce-treasure-location {:lat 8 :lng 2})

(defn announce-treasure-location-v2
  [{:keys [lat lng]}]
  (println lat)
  (println lng))
(announce-treasure-location-v2 {:lat 8 :lng 2})

(map #(str "Hi, " %) ["Romain" "Victor"])

(def add100 #(+ 100 %))
(add100 1)