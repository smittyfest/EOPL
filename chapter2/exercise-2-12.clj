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
;; The Stack interface contains two observers, as opposed to
;; the Environment interface which only had one observer.
;; The change here is that now the function passed back from
;; each constructor must be able to distinguish between the 
;; two observers and pass back the appropriate response.
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty
  {:doc "Returns an empty stack"}
  []
  (fn [func]
    (cond
      (= func 'empty?) true
      :else (throw (IllegalArgumentException.)))))

(defn push
  {:doc "Pushes a new element onto an existing stack"}
  [x s]
  (fn [func]
    (cond
      (= func 'empty?) false
      (= func 'top) x
      (= func 'pop) s
      :else (throw (IllegalArgumentException.)))))

(defn empty?
  {:doc "Returns true if the given stack is empty, false otherwise"}
  [s]
  (s 'empty?))

(defn pop
  {:doc "Removes the top element from a stack"}
  [s]
  (s 'pop))

(defn top
  {:doc "Returns the top element of a stack"}
  [s]
  (s 'top))
;;
;; unit-tests
;;
(def empt (empty))
(is (= (empt 'empty?) true))
(is (= (top (push 2 (push 1 (empty)))) 2))
