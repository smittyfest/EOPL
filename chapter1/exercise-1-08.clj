;; ------------------------------------------------------------------------------
;; Exercise 1.8[*]
;;
(ns eopl.ch01 (:use clojure.test))
(defn remove-first
  {:doc "Remove first occurrence of s from the list xs"}
  [xs s]
  (if (empty? xs)
    ()
    (if (= s (first xs))
      (rest xs)
        (cons (first xs) (remove-first (rest xs) s)))))
;;
;; run some unit-tests
;;
(is (= (remove-first '(a b c) 'a) '(b c)))
(is (= (remove-first '(a b c) 'e) '(a b c)))
(is (= (remove-first () 'a) ()))
(is (= (remove-first '(a b c a b c) 'c) '(a b a b c)))
