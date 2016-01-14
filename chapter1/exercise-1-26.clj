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
    (if (list? (first xs))
      (concat (first xs) (up (rest xs)))
      (cons (first xs) (up (rest xs))))))
;; unit-tests
(is (= (up '((1 2) (3 4))) '(1 2 3 4)))
(is (= (up '((x (y)) z)) '(x (y) z)))
(is (= (up '((a) (b) (c) (d))) '(a b c d)))
