;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.17[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn down
  {:doc "wraps parentheses around each top-level element of a list"}
  [xs]
  (partition 1 xs))
;;
;; unit-tests
;;
(is (= (down ()) ()))
(is (= (down '(1 2 3)) '((1) (2) (3))))
(is (= (down '((a) (fine) (idea))) '(((a)) ((fine)) ((idea)))))
(is (= (down '(a (more (complicated)) object)) '((a) ((more (complicated))) (object))))
