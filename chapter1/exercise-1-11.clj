
;; ------------------------------------------------------------------------------
;; Exercise 1.11[*]
;;
(ns eopl.ch01 (:use clojure.test))
(defn subst
  {:doc "Return a new List with each occurrence of 'old' replaced by 'new'}
  [old new lst]
