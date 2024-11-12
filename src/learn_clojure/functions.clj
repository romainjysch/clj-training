(ns learn-clojure.functions)

(defn greet
  [name]
  (str "Hello, " name))
(greet "Romain")

(defn messenger
  ([] (messenger "Hello, world!"))
  ([msg] (println msg)))
(messenger)
(messenger "Hello, Romain!")

(defn hello
  [msg & who]
  (println msg who))
(hello "hello" "romain" "victor")

((fn [x] (println x)) "romain")
(#(println %) "romain")                                     ;using Clojure reader

(def greet-v2 (fn [x] (println x)))
(greet-v2 "Hello, Romain!")

(def add #(+ %1 %2))
(add 1 2)

(def variadic-println #(println %&))
(variadic-println 1 2 3)

(defn messenger-builder
  [greet]
  (fn [who] (println greet who)))

(def messenger-er (messenger-builder "Hello"))

(messenger-er "Romain")

(defn new-length
  [obj]
  (.length obj))
(new-length "Romain")

;; Test your knowledge
(defn greeting
  ([] (greeting "Hello" "World!"))
  ([x] (greeting "Hello" x))
  ([x y] (str x ", " y "!")))
(greeting)
(greeting "Clojure")
(greeting "Hello" "Romain")

(defn do-nothing
  [x]
  x)
(do-nothing "romain")

(defn always-thing
  [& args]
  100)
(always-thing)
(always-thing 1)
(always-thing 1 2 3)

(defn make-thingy
  [x]
  (fn [& args] x))
(make-thingy 1)

(require '[clojure.repl :refer :all])
(source constantly)

(defn triplicate
  [f]
  (f) (f) (f))
(defn say-hello [] (println "hello"))
(triplicate say-hello)

(defn opposite [f]
   (fn [& args] (not (apply f args))))

(defn triplicate2 [f & args]
  (triplicate (fn [] (apply f args))))
(defn say-hello2 [x] (println "Hello" x))
(triplicate2 say-hello2 "Romain")

(defn http-get [url]
  (slurp url))
(http-get "https://www.google.ch")
(assert (.contains (http-get "https://www.w3.org") "html"))

(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))

(defn two-fns [f g]
  (fn [x] (f (g x))))