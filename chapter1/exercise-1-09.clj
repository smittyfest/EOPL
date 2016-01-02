;; ------------------------------------------------------------------------------
;; Exercise 1.9[**]
;;
(ns eopl.ch01 (:use clojure.test))
(defn remove-all
  {:doc "Remove all occurrences of s from the list xs"}
  [xs s]
  (if (empty? xs)
    ()
    (if (= s (first xs)) (remove-all (rest xs) s)
  (cons (first xs) (remove-all (rest xs) s)))))
;;
;; run some unit-tests
;;
(is (= (remove-all () 'a) ()))
(is (= (remove-all '(a b) 'a) '(b)))
(is (= (remove-all '(a b c) 'b) '(a c)))
(is (= (remove-all '(a b c b d b e f) 'b) '(a c d e f)))
(is (= (remove-all '(a b c b d b e f b) 'b) '(a c d e f)))
(is (= (remove-all '(a b c) 'e) '(a b c)))
