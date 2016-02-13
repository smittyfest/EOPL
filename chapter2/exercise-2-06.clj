;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.6[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Map, Set, and Tree representations of environments.
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  {:doc "Map Representation of an empty environment"}
  []
   {})

(defn extend-env
  {:doc ""}
  [var val env]
  (assoc env var val))

(defn apply-env
  {:doc ""}
  [search-var env]
  (get env search-var))
