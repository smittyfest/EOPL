;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.16[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn var-exp
  [var]
    #(do %))

(defn lambda-exp
  [bound-var body]
    (list 'lambda bound-var body))

(defn app-exp
  [exp1 exp2]
    (list exp1 exp2))
