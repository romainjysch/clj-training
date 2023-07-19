(ns librairies.next_jdbc
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [clojure.edn :as edn]
            [clojure.java.io :as io]))

(def config (edn/read-string (slurp (io/resource "config.edn"))))
(def db-spec (:db-spec config))

(def all-restaurants (jdbc/execute! db-spec ["SELECT * FROM RESTAURANTS"]
                                    {:builder-fn rs/as-lower-maps}))

(defn vectorize-name-and-website [restaurants]
  (vec (map (fn [x] (select-keys x [:nom :site_web])) restaurants)))

(vectorize-name-and-website all-restaurants)