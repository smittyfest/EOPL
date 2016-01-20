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

;; Scheme representation
(def zero 0)
(defn is-zero? [n] (zero? n))
(defn succ [n] (+ n 1))
(defn pred [n] (- n 1))
