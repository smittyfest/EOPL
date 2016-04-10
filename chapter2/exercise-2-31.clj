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
;;
(ns eopl.ch02 (:use clojure.test))
