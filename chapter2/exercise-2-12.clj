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
  {:doc ""}
  []
  (fn [x]
    (cond
    (= x 'empty?) true
    :else (throw (IllegalArgumentException.)))))

(defn push
  {:doc ""}
  [stack x]
  (fn [y]
    (cond
      (= y 'empty?) false
      (= y 'top) x
      (= y 'pop) stack
      :else (throw (IllegalArgumentException.)))))

(defn empty?
  {:doc ""}
  [stack]
  (stack 'empty?))

(defn pop
  {:doc ""}
  [stack]
  (stack 'pop))

(defn top
  {:doc ""}
  [stack]
  (stack 'top))
