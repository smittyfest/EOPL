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

(defn app-exp->rator
  {:doc ""}
  [exp]
    (first exp))

(defn app-exp->rand
  {:doc ""}
  [exp]
    (second exp))

(defn lambda-exp->body
  {:doc ""}
  [exp]
  (first (rest (rest exp))))

(defn lambda-exp->bound-var
  {:doc ""}
  [exp]
    (first (second exp)))
