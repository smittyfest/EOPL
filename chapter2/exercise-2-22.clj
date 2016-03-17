;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.22[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Using "define-datatype" ("deftype" in Clojure),
;; implement the Stack data type of exercise 2.4.
;;
(ns eopl.ch02 (:use clojure.test))

(defprotocol IStack
  (empty-stack [this])
  (empty-stack? [this s])
  (push [this var s])
  (pop [this s])
  (top [this s]))

(deftype Stack []
  IStack
  (empty-stack [this] ())
  (empty-stack? [this s] (prn "tweet!"))
  (push [this var s] (prn "woof!"))
  (pop [this s] (prn "meow!"))
  top [this s] (prn "ribbet!"))
;;
;; unit-tests
;;
