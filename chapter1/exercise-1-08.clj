;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.8[*] @
;; @@@@@@@@@@@@@@@@@@@
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
      
(defn remove-up-to
  {:doc "Remove all elements up to and including the first occurrence of s from the list xs"}
  [xs s]
  (if (empty? xs)
    ()
    (if (= s (first xs))
      (rest xs)
        (remove-up-to (rest xs) s))))
;;
;; unit-tests
;;
(is (= (remove-first '(a b c) 'a) '(b c)))
(is (= (remove-first '(a b c) 'e) '(a b c)))
(is (= (remove-first () 'a) ()))
(is (= (remove-first '(a b c a b c) 'c) '(a b a b c)))
(is (= (remove-up-to '(a b c) 'a) '(b c)))
(is (= (remove-up-to '(a b c) 'e) ()))
(is (= (remove-up-to () 'a) ()))
(is (= (remove-up-to '(a b c a b c) 'c) '(a b c)))
(is (= (remove-up-to '(q r s t u v w x y z) 'y) '(z)))
