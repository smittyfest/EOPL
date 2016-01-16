;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.31[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))

(defn leaf
  {:doc "builds a new bintree from a leaf"}
  [x] x)

(defn interior-node
  {:doc "builds a new bintree from an interior node"}
  [x left right] (list x left right))

(defn leaf?
  {:doc "tests whether a bintree is a leaf"}
  [tree] (if (integer? tree) true false))

(defn lson
  {:doc "returns the left child of a node"}
  [node] (first (rest node)))

(defn rson
  {:doc "returns the right child of a node"}
  [node] (first (rest (rest node))))

(defn contents-of
  {:doc "returns the contents of an interior-node or leaf"}
  [node]
  (cond
    (leaf? node) node
    (empty? node) ()
    :else (first node)))
;;
;; unit-tests
;;
(is (= (leaf 5) 5))
(is (= (interior-node 'x 1 2) '(x 1 2)))
(is (= (interior-node 'x 1 '(y 1 2)) '(x 1 (y 1 2))))
(is (= (leaf? 1) true))
(is (= (leaf? '(x 1 2)) false))
(is (= (lson '(x 1 2)) 1))
(is (= (rson '(x 1 2)) 2))
(is (= (contents-of '(x 1 2)) 'x))
