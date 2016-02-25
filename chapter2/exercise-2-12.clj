;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.12[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; (empty s)     = ()
;; (push [f] s)  = (f . s)
;; (pop s)       = (cdr s)
;; (top s)       = (car s)
;; (empty? s)    = {#t if s = (), #f otherwise}
;;
;; constructors: empty, push, pop
;; observers: top, empty?
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty
  {:doc "Returns an empty stack"}
  []
  (fn [s]
    ()))

(defn push
  {:doc "Pushes a new element onto an existing stack"}
  [x s]
  (fn [x]
    x))

(defn empty?
  {:doc "Returns true if the given stack is empty, false otherwise"}
  [s]
  (= s ()))

(defn pop
  {:doc "Removes the top element from a stack"}
  [s]
  (fn [s] (rest s)))

(defn top
  {:doc "Returns the top element of a stack"}
  [s]
  (first s))
;;
;; unit-tests
;;
(def empt (empty))
(is (= (empt ()) ()))
(is (= (empt '(1 2)) ()))
