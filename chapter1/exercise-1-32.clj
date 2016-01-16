;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.32[*] @
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

(defn double-tree
  {:doc "takes a bintree and returns a bintree with all its contents doubled"}
  [tree]
  (if (leaf? tree)
    (leaf (* 2 (contents-of tree)))
    (interior-node
      (contents-of tree)
      (double-tree (lson tree))
      (double-tree (rson tree)))))
;;
;; unit-tests
;;
(is (= (double-tree '(2 4 6)) '(2 8 12)))
(is (= (double-tree '(2 (x 3 6) (y 5 9))) '(2 (x 6 12) (y 10 18))))
