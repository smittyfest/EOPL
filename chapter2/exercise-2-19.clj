;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.19[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; A Binary-Tree with empty leaves and with interior nodes labeled with integers
;; could be represented by the grammar:
;;
;; Bintree ::= (Int Bintree Bintree)
;;
;; In this representation, implement the procedure number->bintree, which takes
;; a number and produces a binary tree consisting of a single node containing that number.
;; Also implement current-element, move-to-lson, move-to-rson, at-leaf?, insert-to-left, and insert-to-right.
;;
(ns eopl.ch02 (:use clojure.test))

(defn leaf [] ())
(defn at-leaf? [n] (nil? n))

(defn fork [n l r] (list n l r))
(defn current-element [node] (first (seq node)))
(defn move-to-left [exp] (first (rest (seq exp))))
(defn move-to-right [exp] (first (rest (rest (seq exp)))))

(defn number->bintree [n] (fork n (leaf) (leaf)))

(defn insert-to-left
  {:doc ""}
  [n t]
  (fork (current-element t)
    (fork n (move-to-left t) (leaf))
    (move-to-right t)))

(defn insert-to-right
  {:doc ""}
  [n t]
  (fork (current-element t)
    (move-to-left t)
    (fork n (leaf) (move-to-right t))))
  ;;
  ;; unit-tests
  ;;
