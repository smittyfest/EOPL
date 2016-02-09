;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.5[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
;; association-list implementation of environments
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  {:doc "Representation of an empty environment"}
  []
    ())

(defn extend-env
  {:doc "Create a new binding in an environment"}
  [var val env]
  (cons [var val] env))
