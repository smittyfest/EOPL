;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.24[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn every?
  {:doc "Returns true if every element of a list satisfies a predicate, false otherwise"}
  [pred xs]
  (if (empty? xs) true
    (if (pred (first xs)) (every? pred (rest xs)) false)))
;;
;; unit-tests
;;
(is (= (every? number? '(a b c 3 e)) false))
(is (= (every? number? '(1 2 3 5 4)) true))
(is (= (every? number? '(1 2 3 4 5 a)) false))
