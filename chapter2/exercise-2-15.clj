;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.15[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Implement the Lambda Calculus expression interface
;;
(ns eopl.ch02 (:use clojure.test))

(defn var-exp
  {:doc "Identity function"}
  [var]
    (var))
