;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.21[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn product
  {:doc "Returns the Cartesian Product of two Lists"}
  [xs ys]
  (for [x xs y ys] (list x y)))
;;
;; unit-tests
;;
(is (= (product '(a b) ()) ()))
(is (= (product () '(a b)) ()))
(is (= (product '(a) '(b)) '((a b))))
(is (= (product '(a) '(x y z)) '((a x) (a y) (a z))))
(is (= (product '(a b c) '(x)) '((a x) (b x) (c x))))
(is (= (product '(a b c) '(x y)) '((a x) (a y) (b x) (b y) (c x) (c y))))
(is (= (product '(a b c) '(x y z)) '((a x) (a y) (a z) (b x) (b y) (b z) (c x) (c y) (c z))))
