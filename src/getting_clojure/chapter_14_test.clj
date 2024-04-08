(ns getting-clojure.chapter-14-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gene]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ctest]
            [getting-clojure.chapter-14 :as i]))

(run-tests)

(deftest test-finding-books
  (is (not (nil? (i/find-by-title "2001" i/inventory)))))

(deftest test-basic-inventory
  (testing "Finding 2001"
    (is (not (nil? (i/find-by-title "2001" i/inventory)))))
  (testing "Copies of 2001"
    (is (= 21 (i/number-of-copies-of "2001" i/inventory)))))

(gene/sample gene/string-alphanumeric)
(gene/sample gene/uuid)
(gene/sample gene/int)
(gene/sample gene/pos-int)

(def title-gen (gene/such-that not-empty gene/string-alphanumeric))
(gene/sample title-gen)
(def author-gen (gene/such-that not-empty gene/string-alphanumeric))
(gene/sample author-gen)
(def copies-gen (gene/such-that (complement zero?) gene/pos-int))
(gene/sample copies-gen)

(def book-gen
  (gene/hash-map :title title-gen
                 :author author-gen
                 :copies copies-gen))
(gene/sample book-gen)

(def inventory-gen
  (gene/not-empty (gene/vector book-gen)))
(gene/sample inventory-gen)

(def inventory-and-book-gen
  (gene/let [inventory inventory-gen
             book book-gen]
    {:inventory inventory :book book}))
(gene/sample inventory-and-book-gen)

(defn f [a b]
  (/ a b))

(deftest test-f
  (is (= 1/2 (f 1 2)))
  (is (= 1/2 (f 3 6)))
  (is (= 1 (f 10 10))))

(defn more-complex-f [a b]
  (let [denominator (- b 863947)]
    (if (zero? denominator)
      :no-result
      (/ a denominator))))

(deftest test-critical-value
  (is (= :no-result (more-complex-f 1 863947))))