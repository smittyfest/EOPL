;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.30[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(declare merge)
(defn sort-by-predicate
  {:doc "sorts a list of integers by a given predicate"}
  [xs pred]
  (if (> (count xs) 1)
    (let [partitions (split-at (/ (count xs) 2) xs)]
      (merge (sort-by-predicate (get partitions 0) pred) (sort-by-predicate (get partitions 1) pred) pred))
    xs))

(defn merge
  {:doc "given two lists of integers, returns a single sorted list"}
  [xs ys pred]
  (cond
    (empty? xs) ys
    (empty? ys) xs
    (pred (first xs) (first ys)) (cons (first xs) (merge (rest xs) ys pred))
    :else (cons (first ys) (merge xs (rest ys) pred))))
