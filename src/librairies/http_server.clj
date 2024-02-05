(ns librairies.http_server
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]
            [aleph.http :as http]
            [cheshire.core :as json]))

(def people
  [{:756 {:firstname "Romain" :age 24 :city "Lausanne"}}
   {:757 {:firstname "Victor" :age 26 :city "Lausanne"}}])
(println people)

;; text with ring :
(defn handler [request]
  {:body "Hello Clojure!"
   :status 200
   :headers {"Content-Type" "text/html"}})

(jetty/run-jetty handler {:port 12000 :join? false})

;; json with cheshire :
(defn handler [request]
  {:body (json/generate-string people {:pretty true})
   :status 200
   :headers {"Content-Type" "application/json"}})

(http/start-server handler {:port 12000 :join? false})

;; client with clj-http :
(client/get "http://localhost:12000")