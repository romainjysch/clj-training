(ns getting-clojure.chapter-9
  (:require [clojure.data :as data]
            [exercism.cars-assemble :as car]))

;; Chapter 9 : Namespaces

(def books ["Dune" "Star Wars"])
(def movies ["Dune" "Mission Impossible"])

(data/diff books movies)

(println (car/working-items 5))

(println "Current ns:" *ns*)
(find-ns 'getting-clojure.chapter-9)
(ns-map (find-ns 'getting-clojure.chapter-9))
(ns-map 'getting-clojure.chapter-9)
(ns-map 'clojure.core)

