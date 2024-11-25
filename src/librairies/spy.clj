(ns librairies.spy
  (:require [clojure.test :refer :all]
            [spy.core :as spy]
            [spy.assert :as assert]))

(defn adder [x y]
  (+ x y))
(adder 1 2)

;; Spy
(deftest spy-test
  (let [spy-adder (spy/spy adder)]

    (is (= [] (spy/calls spy-adder)))
    (is (= [] (spy/responses spy-adder)))

    (is (true? (spy/not-called? spy-adder)))
    (assert/not-called? spy-adder)

    #_(testing "What a failure looks like"
        (assert/called? spy-adder))

    (testing "Calling the function"
      (is (= 3 (spy-adder 1 2))))

    (testing "Calling the calls"
      (is (= [[1 2]] (spy/calls spy-adder))))

    (testing "Calling the responses"
      (is (= [3] (spy/responses spy-adder))))

    (testing "Another call"
      (is (= 42 (spy-adder 40 2))))

    (testing "Calls and responses are stored using metadata"
      (let [{:keys [calls responses]} (meta spy-adder)]
        (is (= [[1 2] [40 2]] @calls))
        (is (= [3 42] @responses))))

    (testing "Using spy/calls and spy/responses"
      (is (= [[1 2] [40 2]] (spy/calls spy-adder)))
      (is (= [3 42] (spy/responses spy-adder))))

    (testing "Check if called with some arguments"
      (is (true? (spy/called-with? spy-adder 1 2)))
      (is (false? (spy/called-with? spy-adder 1 42))))))

;; Stubs
(deftest stubs-test
  (let [f (spy/stub 42)]
    (is (spy/not-called? f))
    (is (= 42 (f)))
    (is (spy/called? f))
    (is (spy/called-once? f))
    (f)
    (f)
    (is (spy/called-n-times? f 3))))

;; Mocks
(deftest mocks-test
  (let [if-one (fn [x] (if (= 1 x)
                         :one
                         :something-else))
        f (spy/mock if-one)]
    (is (= :one (f 1)))
    (is (spy/called-once? f))
    (is (= :something-else (f 42)))))

(comment
  (require '[eftest.runner :as eftest])
  (eftest/run-tests (eftest/find-tests *ns*))
  :end)
