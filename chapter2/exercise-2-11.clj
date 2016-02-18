
;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.11[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  {:doc "Representation of an empty environment"}
  []
    ())

(defn empty-env?
  {:doc "Returns true if given environment is empty"}
  [env]
  (= env (empty-env)))

;;
;; unit-tests
;;
(is (= (empty-env) ()))
(is (= (empty-env? (empty-env)) true))
