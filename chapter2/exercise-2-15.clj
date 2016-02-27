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

(defn var-exp?
  {:doc "Returns true if the given expression is a variable expression, false otherwise"}
  [exp]
    (symbol? exp))

(defn var-exp->var
  {:doc ""}
  [exp]
    (exp))
