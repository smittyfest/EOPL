;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.14[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; Extend the representation of the preceding exercise to include a
;; third procedure that implements has-binding? (see exercise 2.9).
;;
(ns eopl.ch02 (:use clojure.test))

(def apply-env-func first)
(defn empty-env?-func [env] (first (rest env)))
(defn has-binding?-func [env] (first (rest (rest env))))

(defn report-no-binding-found
  [search-var]
    (println 'apply-env "No binding found for ~s" search-var))

(defn make-env
  [apply-env-func2 empty-env-func2 has-binding?-func2]
    (list apply-env-func2 empty-env-func2 has-binding?-func2))

(defn empty-env
  []
  (make-env (fn [search-var]
    (report-no-binding-found search-var)))
               (fn [] true)
               (fn [search-var] false))

(defn apply-env
  [env search-var]
  (apply-env-func env) search-var)

(defn empty-env?
  [env]
  (empty-env?-func env))

(defn has-binding?
  [env search-var]
  (has-binding?-func env) search-var)

(defn extend-env
  [saved-var saved-val saved-env]
    (make-env (fn [search-var]
                 (if (= search-var saved-var) saved-val
                   (apply-env saved-env search-var)))
               (fn [] false)
               (fn [search-var]
                 (or (= search-var saved-var)
                   (has-binding? saved-env search-var)))))
;;
;; unit-tests
;;
