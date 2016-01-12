;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.25[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn exists?
  {:doc "Returns true if any element in a list satisfies a predicate, false otherwise"}
  [pred xs]
  (if (empty? xs) false
    (if (pred (first xs)) true (exists? pred (rest xs)))))
;;
;; unit-tests
;;
(is (= (exists? number? '(a b c 3 e)) true))
(is (= (exists? number? '(a b c d e)) false))
(is (= (exists? symbol? '((a b) (c d) 1 e)) true))
