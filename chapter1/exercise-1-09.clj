;; ------------------------------------------------------------------------------
;; Exercise 1.9[**]
;;
(ns eopl.ch01 (:use clojure.test))
(defn remove
  {:doc "Remove all occurrences of s from the list xs"}
  [xs s]
