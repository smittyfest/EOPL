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
  (loop [cnt n tmp lst]
    (if (zero? cnt) 
    (first tmp) 
    (recur (dec cnt) (rest tmp)))))

;;
;; run some unit-tests
;;
(is (= (nth-elem '(a b c d e) 3) 'd))

