(ns librairies.http_server
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]
            [aleph.http :as http]
            [cheshire.core :as json]
            [librairies.jdbc :as da]))

(def people-vector
  [{:756 {:firstname "Romain" :age 24 :city "Lausanne"}}
   {:757 {:firstname "Victor" :age 26 :city "Lausanne"}}])
(println people)

(def people-map {:1 {:firstname "Romain"
                     :age 24
                     :city "Lausanne"}
                 :2 {:firstname "Robin"
                     :age 24
                     :city "Fribourg"}
                 :3 {:firstname "Victor"
                     :age 26
                     :city "Neuchâtel"}})

;; text with ring :
(defn handler [request]
  {:body "Hello Clojure!"
   :status 200
   :headers {"Content-Type" "text/html"}})

(jetty/run-jetty handler {:port 12000 :join? false})

;; json with aleph :
(defn handler [request]
  {:body (json/generate-string people-map {:pretty true})
   :status 200
   :headers {"Content-Type" "application/json"}})

(http/start-server handler {:port 12000 :join? false})

;; client with clj-http :
(client/get "http://localhost:12000")