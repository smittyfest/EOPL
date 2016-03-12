;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.17[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

;; A. List with a tag as the first element:
(defn var-exp-a [var] (list 'var var))
(defn lambda-exp-a [bound-var body] (list 'lambda bound-var body))
(defn app-exp-a [exp1 exp2] (list 'app exp1 exp2))

(defn pair? [exp] (and (first exp) (second exp)))
(defn var-exp?-a [exp] (and (pair? exp) (= 'var (first exp))))
(defn lambda-exp?-a [exp] (and (pair? exp) (= 'lambda (first exp))))
(defn app-exp?-a [exp] (and (pair? exp) (= 'app (first exp))))

(defn var-exp->var-a [exp] (first (rest exp)))
(defn lambda-exp->bound-var-a [exp] (first (rest exp)))
(defn lambda-exp->body-a [exp] (first (rest (rest exp))))
(defn app-exp->rator-a [exp] (first (rest exp)))
(defn app-exp->rand-a [exp] (first (rest (rest exp))))

;; B) As Symbol/Vector Pairs:
(defn var-exp-b [var] #(do %))
(defn lambda-exp-b [bound-var body] (vector bound-var body))
(defn app-exp-b [exp1 exp2] (cons exp1 exp2))

(defn var-exp?-b [exp] (symbol? exp))
(defn lambda-exp?-b [exp] (vector? exp))
(defn app-exp?-b [exp] (pair? exp))

(defn var-exp->var-b [exp] exp)
(defn lambda-exp->bound-var-b [exp] (nth exp 0))
(defn lambda-exp->body-b [exp] (nth exp 1))
(defn app-exp->rator-b [exp] (first exp))
(defn app-exp->rand-b [exp] (rest exp))
;;
;; unit-tests
;;
