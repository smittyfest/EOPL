;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.19[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))

(declare list-set-accu)
(defn list-set
  {:doc "Replace nth-element of the list with x"}
  [n x xs]
  (if (> n (count xs))
    xs
    (list-set-accu 0 n x xs)))

(defn- list-set-accu
  {:doc "Accumulator for list-set that checks indices before setting"}
  [accu n x xs]
  (if (= accu n)
    (cons x (rest xs))
    (cons (first xs) (list-set-accu (inc accu) n x (rest xs)))))
;;
;; unit-tests
;;
(is (= (list-set 3 'z ()) ()))
(is (= (list-set 3 'a '(1 2)) '(1 2)))
(is (= (list-set 2 3 '(1 2 4 4)) '(1 2 3 4)))
(is (= (list-set 3 'z '(a b c d e)) '(a b c z e)))
(is (= (list-set 2 '(1 2) '(a b c d)) '(a b (1 2) d)))
(is (= (nth (list-set 3 '(1 5 10) '(a b c d)) 3)))
