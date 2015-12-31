;; ------------------------------------------------------------------------------
;; Exercise 1.6[*]
;;
;; If we reversed the order of the tests in nth-elem,
;; what could possibly go wrong?
;;
(ns eopl.ch01 (:use clojure.test))

(defn nth-elem
  {:doc "Return nth-element of the list"}
  [lst n]
  (loop [count n tmp lst]
    (if (zero? count) 
    (first tmp) 
    (recur (dec count) (rest tmp)))))

;;
;; run some unit-tests
;;
(is (nth-elem '(a b c d e) 3) d)
;; ==> d
