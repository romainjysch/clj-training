(ns getting-clojure.chapter-4)

(def var 1)
(if (= var 1)
  (println "Good")
  (println "Bad"))

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

(if 1
  "I like sf"
  "I like mysteries")

(if [1 2 3]
  "I like sf"
  "I like mysteries")

(if false "I like sf" "I like mysteries")
(if nil "I like sf" "I like mysteries")

(if 1
  (do
    (println "Bonjour")
    (+ 2 2)))

(if 1
  (when
    "Monsieur"
    "Bonjour"))

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

(defn shipping-charge-v2 [preferred-customer? order-amount]
  (cond
    preferred-customer? 0.0
    (< order-amount 50) 5.0
    (< order-amount 100) 10.0
    :else (* 0.1 order-amount)))
(shipping-charge-v2 false 500)
(shipping-charge-v2 false 75)
(shipping-charge-v2 false 5)
(shipping-charge-v2 true 1000)

(defn other-cond-test [x]
  (cond->
    (< 50) "a"
    (< 100) "b"
    (< 200) "c"))
(other-cond-test 25)
(other-cond-test 75)
(other-cond-test 150)

(defn customer-greetings [status]
  (case status
    :gold "Welcome gold"
    :silver "Welcome silver"
    "Welcome"))
(customer-greetings :gold)
(customer-greetings :silver)
(customer-greetings :other)