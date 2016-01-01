;; ------------------------------------------------------------------------------
;; Exercise 1.8[*]
;;
(ns eopl.ch01 (:use clojure.test))
(defn remove-first
  {:doc "Return first-occurrence of s from the list xs"}
  [xs s]
  (if (empty? xs)
  '()
  (loop [tmp xs]
