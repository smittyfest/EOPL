;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.36[***] @
;; @@@@@@@@@@@@@@@@@@@@@@
;;
;; Write a procedure g such that number-elements from page 23
(ns eopl.ch01 (:use clojure.test))

(declare g)

(defn number-elements
  {:doc "takes a list (v0 v1 ..) and returns a list ((0 v0) (1 v1) ..)"}
  [xs]
  (if (empty? xs) ()
    (g (list 0 (first xs)) (number-elements (rest xs)))))

(defn g
  {:doc "handles accumulating count and adding it to list"}
  [x xs]
  (cons x (map (fn [i] (cons (+ (first i) 1) (rest i))) xs)))
;;
;; unit-tests
;;
(is (= (number-elements ()) ()))
(is (= (number-elements '('a 'b 'c)) '((0 'a) (1 'b) (2 'c))))
