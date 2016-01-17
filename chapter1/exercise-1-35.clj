;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.35[***] @
;; @@@@@@@@@@@@@@@@@@@@@@
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

(declare accumulate-leaves)

(defn number-leaves
  {:doc "returns a bintree with the contents of the leaves numbered starting from zero"}
  [tree]
  (accumulate-leaves tree 0))
