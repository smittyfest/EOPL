;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.2[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

;; unary representation
(def zero ())
(defn is-zero? [n] (empty? n))
(defn succ [n] (cons true n))
(defn pred [n] (rest n))
;; The unary representation is a simple implementation since it represents integers as a list of boolean 'true' values.
;; This implementation has constant access time for any integer, but the space complexity increases linearly as n
;; becomes large, resulting in poor memory-efficiency for large values of n.

;; Scheme representation
(def zero 0)
(defn is-zero? [n] (zero? n))
(defn succ [n] (+ n 1))
(defn pred [n] (- n 1))
