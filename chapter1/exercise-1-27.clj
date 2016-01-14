;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.27[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn flatten
  {:doc "takes an arbitrarily-nested list and returns a flat list"}
  [xs]
  (if (empty? xs) ()
    (if (list? (first xs))
      (concat (flatten (first xs)) (flatten (rest xs)))
      (cons (first xs) (flatten (rest xs))))))
;;
;; unit-tests
;;
(is (= (flatten '(a b c)) '(a b c)))
(is (= (flatten '((a) () (b ()) () (c))) '(a b c)))
(is (= (flatten '((a b) c (((d)) e))) '(a b c d e)))
(is (= (flatten '(a b (() (c)))) '(a b c)))
