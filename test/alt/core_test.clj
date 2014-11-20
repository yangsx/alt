(ns alt.core-test
  (:require [clojure.test :refer :all]
            [alt.core :refer :all]))

(deftest no-caluses
  (testing "no clauses at all."
    (is (nil? (alternatives)))))

(deftest first-test
  (testing "no :final or :blessed, the first clause win."
    (is (= (alternatives
             :a 1
             :b 2
             :c 3)
           1))))

(deftest final-test
  (testing "no :blessed clause, :final clause win.")
  (is (= (alternatives
           :a 1
           :final 2
           :c 3)
         2)))

(deftest blessed-test
  (testing ":blessed clause always win.")
  (is (= (alternatives
          :blessed :a
          :a 1
          :b 2)
         1))
  (is (= (alternatives
           :a 1
           :blessed :a
           :final 3)
         1)))

(deftest side-effect-test
  (testing "each clause should not evaluate more than once.")
  (is (= (alternatives
          :a (let [a 1]
               (println a)
               a)
          :b (let [a 2]
               (println a)
               a))
         1)))
