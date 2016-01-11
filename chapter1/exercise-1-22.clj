;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.22[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn filter-in
  {:doc "Returns a list of elements satisfying a predicate"}
  [pred xs]
  (if (empty? xs) ()
    (if (pred (first xs))
      (cons (first xs) (filter-in pred (rest xs)))
      (filter-in pred (rest xs)))))
;;
;; unit-tests
;;
(is (= (filter-in number? '(a 2 (1 3) b 7)) '(2 7)))
(is (= (filter-in symbol? '(a (b c) 17 f)) '(a f)))
