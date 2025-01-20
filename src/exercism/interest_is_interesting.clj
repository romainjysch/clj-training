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

;;

(defn annual-balance-update
  "Returns the annual balance update, taking into account the interest rate."
  [balance]
  (let [balance (bigdec balance)
        rate (-> balance interest-rate (/ 100) bigdec)
        interest (* balance rate)]
    (if (neg? rate)
      (- balance interest)
      (+ balance interest))))

;;

(defn amount-to-donate
  "Returns how much money to donate based on the balance and the tax-free percentage."
  [balance tax-free-percentage]
  (let [tax-free-percentage-divide-by-100 (/ tax-free-percentage 100.0M)]
    (if (>= balance 0)
      (int (* balance tax-free-percentage-divide-by-100 2)) 0)))

(comment
  (type 2.45M)
  (type 2.45)
  (type (bigdec 2.45))
  (= 2.45M (bigdec 2.45))

  (interest-rate 200.75)
  (interest-rate 200.75M)
  (interest-rate 5000M)
  (interest-rate 5000.75M)
  (interest-rate -0.123M)

  (annual-balance-update 200.75M)
  (annual-balance-update 1000.0M)
  (annual-balance-update 898124017.826243404425M)
  (annual-balance-update -0.123M)
  (annual-balance-update -152964.231M)

  (amount-to-donate 550.5M 2.5)
  :end)