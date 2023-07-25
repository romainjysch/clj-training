(ns librairies.next_jdbc
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [clojure.java.io :as io]))

(def config
  (->> (io/resource "config.edn")
       slurp
       read-string))

(def db-spec (:db-spec config))

(println db-spec)

(def all-restaurants (jdbc/execute! db-spec ["SELECT * FROM RESTAURANTS"]
                                    {:builder-fn rs/as-lower-maps}))

(println all-restaurants)

(defn vectorize-name-and-website [restaurants]
  (vec (map (fn [x] (select-keys x [:nom :site_web])) restaurants)))

(vectorize-name-and-website all-restaurants)