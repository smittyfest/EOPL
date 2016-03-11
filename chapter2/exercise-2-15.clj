;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.15[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;;
;; Implement the Lambda Calculus expression interface for the representation
;; specified by the grammar:
;;
;; constructors:
;; var-exp               :  var -> lc-exp
;; lambda-exp            :  var x lc-exp -> lc-exp
;; app-exp               :  lc-exp x lc-exp -> lc-exp
;;
;; predicates:
;; var-exp?              : lc-exp -> bool
;; lambda-exp?           : lc-exp -> bool
;; app-exp?              : lc-exp -> bool
;;
;; extractors:
;; var-exp->var          : lc-exp -> var
;; lambda-exp->bound-var : lc-exp -> var
;; lambda-exp->body      : lc-exp -> lc-exp
;; app-exp->rator        : lc-exp -> lc-exp
;; app-exp->rand         : lc-exp -> lc-exp
;;
(ns eopl.ch02 (:use clojure.test))

(defn var-exp
  [var]
    #(do %)) ;; var is a keyword in clojure, could pick a better parameter name but I like this identity function

(defn lambda-exp
  [bound-var body]
    (list 'lambda bound-var body))

(defn app-exp
  [exp1 exp2]
    (list exp1 exp2))

(defn var-exp?
  {:doc "Returns true if the given expression is a variable expression, false otherwise"}
  [exp]
    (symbol? exp))

(defn pair?
  [exp]
    (and (first exp) (second exp)))

(defn lambda-exp?
  [exp]
    (and (pair? exp) (== 'lambda (first exp))))

(defn app-exp?
  [exp]
    (and (pair? exp) (pair? (rest exp) (nil? (rest (rest exp))))))
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
;;
;; unit-tests
;;
