;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.20[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(declare counter)
(defn count-occurrences
  {:doc "Return the number of occurrences of x in xs"}
  [x xs]
  (if (empty? xs) 0
    (counter 0 x xs)))
