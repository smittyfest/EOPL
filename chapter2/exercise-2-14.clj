;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.14[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  []
  [(fn [] true)
   (fn [search-var] (throw (IllegalStateException.)))])

(defn apply-env
  [search-var env]
  (search-var (first env)))

(defn extend-env
  [var val env]
  [(fn [] false)
   (fn [search-var]
     (if (= var search-var) val
       (apply-env search-var env)))])

(defn empty-env?
  [env]
  ((second env)))

(defn has-binding? [search-var env]
  (search-var (second env)))
;;
;; unit-tests
;;
