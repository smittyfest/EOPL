;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.14[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;;
(ns eopl.ch01 (:use clojure.test))
(declare number-elements-from)
(defn number-elements
  {:doc "takes a list (v0 v1 ..) and returns a list ((0 v0) (1 v1) ..)"}
  [lst]
  (number-elements-from lst 0))

(defn number-elements-from
  {:doc "takes a list (v0 v1 ..) and returns a list ((Int v0) (Int v1) ..)"}
  [lst n]
    (if (empty? lst)
      ()
    (cons
      (list n (first lst))
      (number-elements-from (rest lst) (+ n 1)))))

(is (= (number-elements ()) ()))
(is (= (number-elements '('a 'b 'c)) '((0 'a) (1 'b) (2 'c))))
