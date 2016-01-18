;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.33[**] @
;; @@@@@@@@@@@@@@@@@@@@@
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

(declare accumulate-red-nodes)

(defn mark-leaves-with-red-depth
  {:doc "produces a new bintree where each leaf contains the number of nodes between it and the root that contain the symbol 'red'"}
  [tree]
  (accumulate-red-nodes tree 0))

(defn accumulate-red-nodes
  {:doc "accumulates count of red nodes on each path in a bintree"}
  [tree accu]
  (if (leaf? tree) (leaf accu)
    (let [accu (if (= 'red (contents-of tree)) (inc accu) accu)]
      (interior-node (contents-of tree)
      (accumulate-red-nodes (lson tree) accu)
      (accumulate-red-nodes (rson tree) accu)))))
;;
;; unit-tests
;;
(is (= (mark-leaves-with-red-depth
         (interior-node 'red
         (interior-node 'bar (leaf 26) (leaf 12))
         (interior-node 'red (leaf 11)
         (interior-node 'quux (leaf 117) (leaf 14)))))
           '(red (bar 1 1) (red 2 (quux 2 2)))))
