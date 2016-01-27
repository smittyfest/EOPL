;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.3[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn one
  {:doc "diff-tree representation of one"}
  []
  (list 'one))

(defn diff
  {:doc ""}
  [x y]
  (list x y))
