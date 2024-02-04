(ns librairies.http_server
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]))

(defn handler [request]
  {:body "Hello Clojure!"
   :status 200
   :headers {"Content-Type" "text/html"}})

(jetty/run-jetty handler {:port 12000
                          :join? false})

(client/get "http://localhost:12000")