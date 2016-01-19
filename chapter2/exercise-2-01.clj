;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.1[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn zero
  {:doc "returns bigint representation of zero"}
  ())

(defn zero?
  {:doc "returns true if n is equal to the bigint representation of zero"}
  [n]
  (empty? n))

(defn successor
  {:doc "returns the successor to n in bigint representation"}
  [n]

(defn predecessor
  {:doc "returns the predecessor to n in bigint representation"}
  [n]
