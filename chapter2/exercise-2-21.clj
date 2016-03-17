;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.21[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Implement the data type of environments, as in section 2.2.2, using
;; "define-datatype" ("deftype" in Clojure). Then include "has-binding?" of exercise 2.9.
;;
(ns eopl.ch02 (:use clojure.test))

(defprotocol IEnvironment
  (empty-env [this])
  (extend-env [this var val])
  (apply-env [this var env]))

(deftype Environment []
  IEnvironment
  (empty-env [this] ())
  (extend-env [this var val] (prn "woof!"))
  (apply-env [this val env] (prn "meow!")))
;;
;; unit-tests
;;
