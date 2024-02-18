(ns getting-clojure.chapter-7)

;; Chapter 7 : Let

(defn compute-discount-amount [amount discount-percent min-charge]
  (let [discounted-amount (* amount (- 1.0 discount-percent))]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))
(compute-discount-amount 10.0 0.20 1.0)
(compute-discount-amount 10.0 0.20 20.0)
(compute-discount-amount 10.0 0.20 10.0)

;; no (def ...) inside of a function
;; only (let ...) ! let go away

(defn compute-discount-amount-v2 [amount discount-percent min-charge]
  (let [discount (* amount discount-percent)
        discounted-amount (- amount discount)] ; discount can be used for discounted-amount
    (println "Discount:" discount) ; print something in the (let) body
    (println "Discount amount:" discounted-amount)
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))
(compute-discount-amount-v2 10.0 0.2 1.0)
(compute-discount-amount-v2 10.0 0.2 20.0)

;; only the last expression of a let function get returned

(def user-discounts {:romain 0.10 :felicia 0.05 :robin 0.20})
(user-discounts :romain)
(defn make-discount-price-f [user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)]
    (fn [amount] ; then discount-percent is used inside the anonymous function
      (let [discount (* amount discount-percent)
            discounted-amount (- amount discount)]
        (if (> discounted-amount min-charge)
          discounted-amount
          min-charge)))))
(def compute-felicia-price (make-discount-price-f :felicia user-discounts 10.0))
(compute-felicia-price 20.0)
(def compute-romain-price (make-discount-price-f :romain user-discounts 5.0))
(compute-romain-price 10.0)

(def anonymous-book {:title "Dune"})
(def book-with-author {:title "Dune" :author "Herbert"})

(defn uppercase-author [book]
  (let [author (book :author)]
    (if author
      (.toUpperCase author))))
(uppercase-author anonymous-book)
(uppercase-author book-with-author)

(defn uppercase-author-v2 [book]
  (if-let [author (book :author)] ; single binding
    (.toUpperCase author)
    "ANONYMOUS")) ; return "ANONYMOUS" if nil
(anonymous-book :author)
(uppercase-author-v2 anonymous-book)
(uppercase-author-v2 book-with-author)

(defn bigger-than-5 [x]
 (if-let [is-bigger (> x 5)] ; put true in is-bigger
   is-bigger
   "Not bigger"))
(bigger-than-5 4) ; return true
(bigger-than-5 6)

(defn uppercase-author-v3 [book]
  (when-let [author (book :author)]
    (.toUpperCase author)))
(uppercase-author-v3 anonymous-book)
(uppercase-author-v3 book-with-author)

(let [title "Dune"]
  (let [title "Dune 2"]
    (println title)))

(let [title "Dune"]
  (let [title (str title " 2")]
    (println title)))