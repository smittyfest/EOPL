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
  {:doc ""}
  [x y]
  (list 'diff x y))

(def zero (diff one one))

(defn successor
  {:doc ""}
  [n]
  (plus n (one)))

(defn predecessor
  {:doc ""}
  [n]
  (diff n (one)))

(defn plus
  {:doc ""}
  [x y]
  (diff x (diff (zero) y)))

;; Every number has infinitely-many representations in this system
;; because n = (n + 1) - 1, (n + 2) - 2, ..., (n + k) - k.
