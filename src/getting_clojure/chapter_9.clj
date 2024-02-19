(ns getting-clojure.chapter-9
  (:require :reload [clojure.data :as data]
            [exercism.cars-assemble :as car]
            [getting-clojure.chapter-8 :as chap8]))

;; Chapter 9 : Namespaces

(* chap8/PI 2)

(def books ["Dune" "Star Wars"])
(def movies ["Dune" "Mission Impossible"])
(data/diff books movies) ; vector of 3 vectors : first only ; second only ; both
(second (first (data/diff books movies))) ; return "Star Wars"

(println (car/working-items 5))

(println "Current ns:" *ns*)
(find-ns 'getting-clojure.chapter-9)
(find-ns 'getting-clojure.chapter-8)

(ns-map (find-ns 'getting-clojure.chapter-9))
(ns-map 'getting-clojure.chapter-9) ; shorter version
(ns-map 'clojure.core)

(namespace 'car/working-items)

;; defonce function in order to counter :reload
(defonce f-with-side-effects (car/working-items 5)) ; get evalued once
(println f-with-side-effects)