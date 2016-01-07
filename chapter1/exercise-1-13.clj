
;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.13[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; In our example, we began by eliminating the Kleene Star in the
;; grammar for S-List. Write subst following the original grammar by using map.
;;
;; ~ Original Grammar ~
;; S-List ::= ({S-Exp}*)
;;  S-Exp ::= Symbol | S-List
;;
(ns eopl.ch01 (:use clojure.test))
(defn subst
