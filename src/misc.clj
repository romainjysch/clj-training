(ns misc
  (:require [clojure.string :as str]))

;; anonymous functions
(def multiply-by-3 #(* 3 %))
(multiply-by-3 3)
(multiply-by-3 3)

(def multiply #(* %1 %2))
(multiply 2 3)

(def my-double (fn [x] (* 2 x)))
(my-double 2)

;; partial
(def add-100
  "100 is the first arg to the + function."
  (partial + 100))
(add-100 4)
(add-100 4 4)

(def multiply-by-100
  "100 is the first arg to the * function."
  (partial * 100))
(multiply-by-100 2)
(multiply-by-100 2 2)

(def times (partial *))
(times 1 2 3)

;; complement
(map (complement even?) [1 2 3 4])
(map odd? [1 2 3 4])

(defn fizz?
  [x]
  (zero? (rem 5 x)))
(fizz? 10)

(def reverse-fizz? (complement fizz?))
(reverse-fizz? 5)

;; into
(into [] (map inc #{1 2 3}))
(into {:a 1} {:a 2 :b 2 :c 3})

;; split
(let [zones "ch-gva-2,ch-dk-2,at-vie-1,at-vie-2,de-fra-1,de-muc-1,bg-sof-1"
      result (str/split zones #",")]
  result)

(let [zones "ch-gva-2,bg-sof-1"
      result (str/split zones #",")]
  (count result))

(str/split (str 10) "")