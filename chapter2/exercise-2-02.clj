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

;; Clojure representation
(def zero 0)
(defn is-zero? [n] (zero? n))
(defn succ [n] (+ n 1))
(defn pred [n] (- n 1))
;; Creates a new abstraction layer that delegates the implementation to Clojure.
;; This will help clients remain unaffected if we decide to change the representation of the
;; data specification for any reason.
;; The performance of this implementation is similar to that of Clojure and the JVM.
