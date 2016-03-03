;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.16[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn var-exp
  [var]
    #(do %))

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
