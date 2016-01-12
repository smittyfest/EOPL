;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.26[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn up
  {:doc "Removes a pair of parentheses from each top-level element of a List"}
  [xs]
  (if (empty? xs) ()
    (if (symbol? (first xs)) (cons (first xs) (up (rest xs)))
      (up (first xs)))))
;; unit-tests
(is (= (up '((1 2) (3 4))) '(1 2 3 4)))
(is (= (up '((x (y)) z)) '(x (y) z)))
