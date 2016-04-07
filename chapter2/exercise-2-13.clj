;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.13[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; Extend the procedural representation to implement empty-env?
;; by representing the environment by a list of two procedures: one that returns the
;; value associated with a variable, as before, and one that returns whether or not the
;; environment is empty.
;;
(ns eopl.ch02 (:use clojure.test))

(def make-env cons)
(def apply-env-func first)
(def empty-env?-func rest)

(defn report-no-binding-found
  [search-var]
  (println 'apply-env "No binding found for ~s" search-var))

(defn empty-env
  []
  [(fn [] true)
   (fn [search-var] (throw (IllegalStateException.)))])

(defn empty-env2
  []
  (make-env (fn [search-var]
              (report-no-binding-found search-var))
            (fn [] true)))

(defn apply-env
  [search-var env]
  (search-var (first env)))

(defn apply-env2
  [search-var env]
  ((apply-env-func env) search-var))

(defn extend-env
  [var val env]
  [(fn [] false)
   (fn [search-var]
     (if (= var search-var) val
       (apply-env search-var env)))])

(defn extend-env2
  [saved-var saved-val saved-env]
  (make-env (fn [search-var]
              (if (= search-var saved-var) saved-val
                (apply-env2 search-var saved-env))
            (fn [] false))))

(defn empty-env?
  [env]
  ((rest env)))

(defn empty-env2?
  [env]
  (empty-env?-func env))
;;
;; unit-tests
;;
