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
  ([tree] (number-leaves tree 0))
  ([tree accu]
   (if (leaf? tree)
     (leaf accu)
     (interior-node
       (contents-of tree)
       (number-leaves (lson tree) accu)
       (number-leaves (rson tree)
         (+ accu (accumulate-leaves (lson tree))))))))

(defn accumulate-leaves
  {:doc "iterates over the nodes of a tree, building a new tree with the accumulated values"}
  [tree]
  (if (leaf? tree) 1
    (+ (accumulate-leaves (lson tree))
      (accumulate-leaves (rson tree)))))
;;
;; unit-tests
;;
(is (= (number-leaves (leaf 12)) 0))
(is (= (number-leaves (interior-node 'hello 2 4)) '(hello 0 1)))
(is (= (number-leaves (interior-node 'hello 4 (interior-node 'goodbye 8 9))) '(hello 0 (goodbye 1 2))))
(is (= (number-leaves
         (interior-node 'hello (interior-node 'kitty (interior-node 'naughty 10 20) (interior-node 'nice 30 40))
         (interior-node 'puppy (interior-node 'sleepy 50 60) (interior-node 'playful 70 80))))
           '(hello (kitty (naughty 0 1) (nice 2 3)) (puppy (sleepy 4 5) (playful 6 7)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; first attempt          ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(declare accumulate-leaves-fa)
(defn number-leaves-fa
  {:doc "returns a bintree with the contents of the leaves numbered starting from zero"}
  [tree]
  (accumulate-leaves-fa tree 0))

(defn accumulate-leaves-fa
  {:doc "iterates over the nodes of a tree, building a new tree with the accumulated values"}
  [tree accu]
  (if (leaf? tree) (cons (inc accu) (list (leaf accu)))
  (let [[left-accu & left-tree] (accumulate-leaves-fa (lson tree) accu)
        [right-accu & right-tree] (accumulate-leaves-fa (rson tree) left-accu)]
    (cons right-accu (interior-node (contents-of tree) left-tree right-tree)))))
