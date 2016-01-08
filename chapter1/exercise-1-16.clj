;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.16[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn invert
  {:doc "Reverses the contents of each tuple in a list of tuples"}
  [xs]
  (if (empty? xs)
    ()
    (cons (reverse (first xs)) (invert (rest xs)))))
;;
;; unit-tests
;;
(is (= (invert ()) ()))
(is (= (invert '((b a))) '((a b))))
(is (= (invert '((b a) (d c) (f e) (h g) (j i))) '((a b) (c d) (e f) (g h) (i j))))
(is (= (invert '((a 1) (a 2) (1 b) (2 b))) '((1 a) (2 a) (b 1) (b 2))))
