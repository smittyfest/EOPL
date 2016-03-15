;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.23[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; The definition of lc-exp ignores the condition in definition 1.1.8
;; that says "Identifier is any symbol other than lambda." Modify the definition of
;; "identifier?" to capture this condition. As a hint, remember that any predicate
;; can be used in "define-datatype" ("deftype" in Clojure), even ones you define.
;;
(ns eopl.ch02 (:use clojure.test))
