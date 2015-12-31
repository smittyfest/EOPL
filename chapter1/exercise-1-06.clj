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
(is (= (nth-elem '(a b c d e) 4) 'e))
(is (= (nth-elem '(a b c d e) 3) 'd))
(is (= (nth-elem '(a b c d e) 2) 'c))
(is (= (nth-elem '(a b c d e) 1) 'b))
(is (= (nth-elem '(a b c d e) 0) 'a))
(is (= (nth-elem '(a b c d e) 8) nil))

