;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.15[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn duple
  {:doc "Returns a list containing n copies of x"}
  [x n]
  (if (zero? n)
    ()
    (cons x (duple x (- n 1)))))
  
;; unit-tests
(is (= (duple 5 0) ()))
(is (= (duple 5 3) '(5 5 5)))
(is (= (duple 'a 10) '(a a a a a a a a a a)))
