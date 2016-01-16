;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.34[***] @
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

(defn path
  {:doc "returns a list of lefts and rights needed to find a node contining the integer n"}
  [bst n]
  (if (= n (contents-of bst)) ()
    (if (< n (contents-of bst)) (cons 'left (path (lson bst) n))
      (cons 'right (path (rson bst) n)))))
;;
;; unit-tests
;;
(is (= (path '(14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))) 17) '(right left left)))
