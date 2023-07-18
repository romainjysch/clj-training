(ns exercism.interest-is-interesting)

;; Interest is Interesting
;; https://exercism.org/tracks/clojure/exercises/interest-is-interesting

(defn interest-rate
  "Returns the interest rate based on the specified balance."
  [balance]
  (cond
    (< balance 0) -3.213
    (and (>= balance 0) (< balance 1000)) 0.5
    (and (>= balance 1000) (< balance 5000)) 1.621
    :else 2.475))

(interest-rate 200.75M)
(interest-rate 5000.75M)

;;

(defn interest-rate-divide-by-100 [balance]
  (let [interest-rate (interest-rate balance)]
    (/ interest-rate 100)))

(interest-rate-divide-by-100 200.75M)

(defn annual-interest [balance]
  (let [multiplier (interest-rate-divide-by-100 balance)]
    (bigdec (* balance multiplier))))

(annual-interest 200.75M)

(defn annual-balance-update
  "Returns the annual balance update, taking into account the interest rate."
  [balance]
  (let [annual-interest (annual-interest balance)]
    (bigdec (+ balance annual-interest))))

(annual-balance-update 200.75M)
(annual-balance-update 1000.0M)
(annual-balance-update 898124017.826243404425M)
(annual-balance-update -0.123M)
(annual-balance-update -152964.231M)

;;

(def balance 550.5M)

(annual-balance-update balance)

(defn amount-to-donate
  "Returns how much money to donate based on the balance and the tax-free percentage."
  [balance tax-free-percentage]
  (let [tax-free-percentage-divide-by-100 (/ tax-free-percentage 100.0M)]
    (if (>= balance 0)
      (int (* balance tax-free-percentage-divide-by-100 2)) 0)))

(amount-to-donate 550.5M 2.5)