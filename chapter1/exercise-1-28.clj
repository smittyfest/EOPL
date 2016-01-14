;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.28[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn merge
  {:doc "given two lists of integers, returns a single sorted list"}
  [xs ys]
  (cond
    (empty? xs) ys
    (empty? ys) xs
    (<= (first xs) (first ys)) (cons (first xs) (merge (rest xs) ys))
    :else (cons (first ys) (merge xs (rest ys)))))
;;
;; unit-tests
;;
(is (= (merge '(1 4) '(1 2 8)) '(1 1 2 4 8)))
