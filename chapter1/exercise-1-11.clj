
;; ------------------------------------------------------------------------------
;; Exercise 1.11[*]
;;
;; S-list ::= ()
;;        ::= (S-exp . S-list)
;;  S-exp ::= Symbol | S-list
;;
(ns eopl.ch01 (:use clojure.test))
(defn subst
  {:doc "Return a new List with each occurrence of 'old' replaced by 'new'}
  [old new lst]
  (if (empty? lst)
      ()
      
