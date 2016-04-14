;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.4[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Consider the data type of Stacks of values, with an interface consisting
;; of the procedures empty, push, pop, top, and empty?. Write a specification
;; for these operations in the style of the example above. Which operations
;; are constructors and which are observers?
;;
(ns eopl.ch02 (:use clojure.test))

;; Specification
(empty)       = ()
(push [f] s)  = (f . s)
(pop s)       = (cdr s)
(top s)       = (car s)
(empty? s)    = {#t if s = (), #f otherwise}

;; constructors
empty
push
pop

;; observers
top ;; top or peek is usually an observer, but here acts like both a constructor and an observer
empty?
