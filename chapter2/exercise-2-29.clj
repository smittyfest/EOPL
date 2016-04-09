;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.29[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Where a Kleene Star or Kleene Plus is used in concrete syntax, it
;; is most convenient to use a list of associated subtrees when constructing
;; an abstract syntax tree. For example, if the grammar for lambda-calculus
;; expressions had been:
;;
;; LcExp ::= Identifier
;;           |var-exp (var)|
;;       ::= (lambda ({Identifier}*) LcExp)
;;           |lambda-exp (bound-vars body)|
;;       ::= (LcExp {LcExp}*)
;;           |app-exp (rator rands)|
;;
;; then the predicate for the bound-vars field could be (list-of identifier?),
;; and the predicate for the rands field could be (list-of lc-exp?).
;; Write a define-datatype and a parser for this grammar that works in this way.
;;
(ns eopl.ch02 (:use clojure.test))
