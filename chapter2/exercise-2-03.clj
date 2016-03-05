;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.3[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Diff-Tree ::= (one) | (diff Diff-Tree Diff-Tree)
;;
;; 1. Every number has infinitely-many representations in this system
;;    because n = (n + 1) - 1, (n + 2) - 2, ..., (n + k) - k.
;;
;; 2. Turn this representation of the integers into an implementation by
;;    writing zero, is-zero?, successor and predecessor, as specified on
;;    page 32, except that now the negative integers are also represented. Your
;;    procedures should take as input any of the multiple legal representations
;;    of an integer in this scheme. For example, if your successor procedure is
;;    given any of the infinitely many legal representations of 1, it should
;;    produce one of the legal representations of 2. It is permissible for
;;    different legal representations of 1 to yield different representations
;;    of 2.
;;
;; 3. Write a proedure diff-tree-plus that does addition in this
;;    representation. Your procedure should be optimized for the diff-tree
;;    representation, and should do its work in a constant amount of time
;;    (independent of the size of its inputs). In particular, it should not be
;;    recursive.
;;
(ns eopl.ch02 (:use clojure.test))

;; here are some constructors for the representation:
(def one (list 'one))

(defn diff
  {:doc "returns a diff-tree representation of two integers"}
  [x y]
  (list 'diff x y))

;; Here are some observers:
(defn one?
  [diff-tree]
  (== (first diff-tree) 'one))

(defn diff?
  [diff-tree]
    (== (first diff-tree) 'diff))
  
(defn diff-first
  [diff-tree]
    (first (rest diff-tree)))

(defn diff-second
  [diff-tree]
    (first (rest (rest diff-tree))))

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
