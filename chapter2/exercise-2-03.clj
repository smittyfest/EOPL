;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.3[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Diff-Tree ::= (one) | (diff Diff-Tree Diff-Tree)
;;
(ns eopl.ch02 (:use clojure.test))

(def one (list 'one))

(defn diff
  {:doc "returns a diff-tree representation of two integers"}
  [x y]
  (list 'diff x y))

(def zero (diff one one))

(defn successor
  {:doc "returns the successor of an integer in diff-tree representation"}
  [n]
  (plus n (one)))

(defn predecessor
  {:doc "returns the predecessor of an integer in diff-tree representation"}
  [n]
  (diff n (one)))

(defn plus
  {:doc "adds two integers in diff-tree representation"}
  [x y]
  (diff x (diff (zero) y)))

(defn minus
  {:doc "returns the difference between two diff-tree representations"}
  [n]
  (diff zero n))

(defn negate
  {:doc ""}
  [n]
  (if (= (one n))
    (diff zero one)
    (diff (rest (rest n)) (rest n))))

;; diff-tree evaluation
(defn eval
  {:doc "Evaluates a Diff-Tree expression"}
  [n]
  (if (= (one n))
  
;; Every number has infinitely-many representations in this system
;; because n = (n + 1) - 1, (n + 2) - 2, ..., (n + k) - k.
;;
