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
