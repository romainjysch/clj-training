(ns misc)

;; Anonymous functions :

(def multiply-by-3 #(* 3 %))
(multiply-by-3 3)
(multiply-by-3 3)

(def multiply #(* %1 %2))
(multiply 2 3)

(def my-double (fn [x] (* 2 x)))
(my-double 2)

;; Partial :

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

;; Complement :

(map (complement even?) [1 2 3 4])
(map odd? [1 2 3 4])

(defn fizz?
  [x]
  (zero? (rem 5 x)))
(fizz? 10)

(def reverse-fizz? (complement fizz?))
(reverse-fizz? 5)