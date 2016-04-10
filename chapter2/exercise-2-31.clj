;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.31[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; Sometimes it is useful to specify a concrete syntax as a sequence
;; of symbols and integers, surrounded by parentheses.
;; For example, one might define the set of prefix-lists by:
;;
;; Prefix-List ::= (Prefix-Exp)
;; Prefix-Exp  ::= Int
;;             ::= - Prefix-Exp Prefix-Exp
;;
;; so that (- - 3 2 - 4 - 12 7) is a legal prefix-list. This is sometimes called
;; Polish Prefix Notation, after its inventor, Jan Lukasiewicz.
;;
;; Write a parser to convert a prefix-list to the abstract syntax:
;;
;; (define-datatype prefix-exp prefix-exp?
;;   (const-exp
;;     (num integer?))
;;   (diff-exp
;;     (operand1 prefix-exp?)
;;     (operand2 prefix-exp?)))
;;
;; so that the example above produces the same abstract syntax tree as the sequence
;; of constructors:
;;
;; (diff-exp
;;   (diff-exp
;;     (const-exp 3)
;;     (const-exp 2))
;;   (deff-exp
;;     (const-exp 4)
;;     (diff-exp
;;       (const-exp 12)
;;       (const-exp 7))))
;;
;; As a hint, consider writing a procedure that takes a list and produces a prefix-exp
;; and the list of leftover list elements.
;;
(ns eopl.ch02 (:use clojure.test))
