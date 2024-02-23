(ns getting-clojure.chapter-18)

;; Clojure comes with a variety of containers for mutable state

(def counter (atom 0)) ; instead of (def counter 0)
(defn greeting-message [request]
  (swap! counter inc) ; takes an atom and a function to produce the next value
  (if (zero? (mod counter 100))
    (str "You are the " counter " visitor!")
    (str "Hello there!")))
(greeting-message "test")

;; swap! is thread safe

(swap! counter inc)
@counter
(deref counter)

;; wrap atom around any Clojure value

(def by-title (atom {}))
(defn add-book [{title :title :as book}]
  (swap! by-title #(assoc % title book)))

(defn del-book [title]
  (swap! by-title #(dissoc % title)))

(defn find-book [title]
  (get @by-title title))

(find-book "Dune")
(add-book {:title "Dune", :copies 2000})
(add-book {:title "1984", :copies 1000})
(del-book "1984")
(find-book "Dune")
(find-book "1984")

(def by-title-ref (ref {}))
(def total-copies (ref 0))
@by-title-ref
@total-copies

(defn add-book-ref [{title :title :as book}]
  (dosync
    (alter by-title-ref (fn [by-title-map] (assoc by-title-map title book)))
    (alter total-copies + (:copies book))))
(add-book-ref {:title "Harry Potter", :copies 500})
(add-book-ref {:title "Dune", :copies 1000})
(add-book-ref {:title "1984", :copies 999})
@by-title-ref
@total-copies

;; ref uses dosync and alter functions

(def by-title-agent (agent {}))
(deref by-title-agent)
@by-title-agent
(defn add-book-agent [{title :title :as book}]
  (send
    by-title-agent
    (fn [by-title-map]
      (assoc by-title-map title book))))
(add-book-agent {:title "Dune", :copies 25})

;; update function will get called once

;; var if mostly stable
;; ref if databaselike transaction and no side effect
;; agent if side effect
;; atom if side effect free and no need to keep several values consistent

(defn blurb [book]
  (str "Dont miss the new book, "
       (:title book)
       " by "
       (:author book)))
(def memoized-blurb (memoize blurb)) ; memoize for caching the result of cuntion call

(def dune {:title "Dune", :author "Herbert"})
(memoized-blurb dune) ; for pure functions !

(defn -main []
  ;; Do some stuff with agents.
  ;; Shut down the threads that have been running the agent updates
  ;; so that the JVM will actually shut down.
  (shutdown-agents)) ; good idead to call shutdown-agents before the end of any program that uses agents
