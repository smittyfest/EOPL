;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.3[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Diff-Tree ::= (one) | (diff Diff-Tree Diff-Tree)
;;
(ns eopl.ch02 (:use clojure.test))

;; here are some constructors for the representation:
(def one (list 'one))

(defn diff
  {:doc "returns a diff-tree representation of two integers"}
  [x y]
  (list 'diff x y))

;; Here are some observers:
(def zero (diff one one))

(defn minus
  {:doc "returns the difference between two diff-tree representations"}
  [n]
  (diff zero n))

(defn plus
  {:doc "adds two integers in diff-tree representation"}
  [x y]
  (diff x (minus y)))

(defn negate
  {:doc ""}
  [n]
  (if (= (one n))
    (diff zero one)
    (diff (rest (rest n)) (rest n))))

(defn successor
  {:doc "returns the successor of an integer in diff-tree representation"}
  [n]
  (plus n (one)))

(defn predecessor
  {:doc "returns the predecessor of an integer in diff-tree representation"}
  [n]
  (diff n (one)))

;; Every number has infinitely-many representations in this system
;; because n = (n + 1) - 1, (n + 2) - 2, ..., (n + k) - k.
;;
