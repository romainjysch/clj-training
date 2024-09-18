(ns getting-clojure.chapter-4)

;; Chapter 4 : Logic

(defn print-greeting [preferred-customer?]
  (if preferred-customer?
    (println "Hello my fav!")
    (println "Hello!")))
(print-greeting true)
(print-greeting false)

(defn single-line-print-greeting [preferred-customer]
  (if preferred-customer "Hello my fav"))
(single-line-print-greeting true)
(single-line-print-greeting false) ; return nil

(= 2 2 2 2 2)
(= 2 2 2 3 2) ; = is identical to the Java equals method

(defn x-bigger-than-y [x y]
  (if (> x y) "x is bigger than y"))
(x-bigger-than-y 5 3)
(x-bigger-than-y 3 3)


(def var 1)
(if (= var 1)
  (println "Good")
  (println "Bad"))
(= var 2)

(= (+ 2 2) (/ 40 10) (- 5 1))
(not= "Romain" "Robin")
(= "Romain" "Robin")

(number? 1984)
(number? "Romain")
(string? 1984)
(string? "Romain")
(keyword? :1984)
(keyword? 1984)
(map? :1984)
(map? {:a 1 :b 2})
(vector? 1984)
(vector? [1984])

(defn shipping-surcharge? [preferred-customer express oversized]
  (and (not preferred-customer) (or express oversized)))
(shipping-surcharge? true true true)
(shipping-surcharge? false true false)
(shipping-surcharge? false false false)

(if 1
  "I like sf"
  "I like mysteries")

(if [1 2 3]
  "I like sf"
  "I like mysteries")

(if false "I like sf" "I like mysteries") ; only false and nil aren't true
(if nil "I like sf" "I like mysteries") ; same

;; two falsy things : false and nil ; and infinite number of truthy things

(if 1
  (do
    (println "Bonjour")
    (+ 2 2)))

(if true
  (do
    (println "R")
    "R"
    (println "J")
    "J"))

(do)

(defn shipping-fees [prefered-customer order-amount]
  (if prefered-customer
    (do
      (println "Free for the best!")
      0.00)
    (do
      (println "You are not the best, so...")
      (* 0.1 order-amount)))) ; 10% fees
(shipping-fees true 100)
(shipping-fees false 100)

(if 1
  (when ; like do but with a if that needs to be true
    "Monsieur"
    (println "hello")
    "Bonjour"))

(defn another-greeting [preferred-customer]
  (when preferred-customer ; supports multiple statements without needing do
    (println "Hello returning customer!")
    (println "Welcome back to Blotts Books!")))
(another-greeting true)
(another-greeting false) ; return nil

(defn shipping-charge [preferred-customer? order-amount]
  (if preferred-customer?
    0.0
    (if (< order-amount 50)
      5.0
      (if (< order-amount 100)
        10.0
        (* 0.1 order-amount)))))
(shipping-charge false 500)
(shipping-charge false 75)
(shipping-charge false 5)
(shipping-charge true 1000)

(defn shipping-charge-v2 [preferred-customer order-amount]
  (cond
    preferred-customer 0.0
    (< order-amount 50) 5.0
    (< order-amount 100) 10.0
    :else (* 0.1 order-amount))) ; :else is always truthy
(shipping-charge-v2 false 500)
(shipping-charge-v2 false 75)
(shipping-charge-v2 false 5)
(shipping-charge-v2 true 1000)

(defn customer-greetings [status]
  (case status
    :gold "Welcome gold"
    :silver "Welcome silver"
    "Welcome")) ; constant, so not evalued
(customer-greetings :gold)
(customer-greetings :silver)
(customer-greetings :other)

(try
  (/ 0 0)
  (catch ArithmeticException e (println "Pas par 0!!")))

(def task)
(type (def task))
(defn ensure-task-is-a-vector [task]
  (if (vector? task)
    task [task]))
(type (ensure-task-is-a-vector task))

(== 5.0 5) ; type independant
(= 5.0 5)

(and 1984 "Romain" 1291)
(and 1984 nil 1291)

(defn equal-or-bigger-than-5 [x]
  (cond
    (= x 5) "5"
    (> x 5) "Bigger than 5"
    :else "Else"))
(equal-or-bigger-than-5 5)
(equal-or-bigger-than-5 10)
(equal-or-bigger-than-5 2)