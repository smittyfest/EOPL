;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.36[***] @
;; @@@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))

(declare g)

(defn number-elements
  {:doc "takes a list (v0 v1 ..) and returns a list ((0 v0) (1 v1) ..)"}
  [xs]
  (if (empty? xs) ()
    (g (list 0 (first xs)) (number-elements (rest xs)))))
