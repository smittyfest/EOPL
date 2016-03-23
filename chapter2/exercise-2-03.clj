;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.3[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Define a representation of all the integers (negative and non-negative)
;; as Diff-Trees, where a Diff-Tree is a list defined by the grammar:
;;
;; Diff-Tree ::= (one) | (diff Diff-Tree Diff-Tree)
;;
;; 1. Every number has infinitely-many representations in this system
;;    because n = (n + 1) - 1, (n + 2) - 2, ..., (n + k) - k.
;;
;; 2. Turn this representation of the integers into an implementation by
;;    writing zero, is-zero?, successor and predecessor, as specified on
;;    page 32, except that now the negative integers are also represented. Your
;;    procedures should take as input any of the multiple legal representations
;;    of an integer in this scheme. For example, if your successor procedure is
;;    given any of the infinitely many legal representations of 1, it should
;;    produce one of the legal representations of 2. It is permissible for
;;    different legal representations of 1 to yield different representations
;;    of 2.
;;
;; 3. Write a procedure diff-tree-plus that does addition in this
;;    representation. Your procedure should be optimized for the diff-tree
;;    representation, and should do its work in a constant amount of time
;;    (independent of the size of its inputs). In particular, it should not be
;;    recursive.
;;
(ns eopl.ch02 (:use clojure.test))

;; constructors for the representation:
(def one (list 'one))

(defn diff
  {:doc "returns a diff-tree representation of two integers"}
  [x y]
  (list 'diff x y))

;; observers:
(defn one?
  [diff-tree]
  (== (first diff-tree) 'one))

(defn diff?
  [diff-tree]
    (== (first diff-tree) 'diff))
  
(defn diff-first
  [diff-tree]
    (first (rest diff-tree)))

(defn diff-second
  [diff-tree]
    (first (rest (rest diff-tree))))

;; higher-level observers: minuend and subtrahend (credit: skanev)
(defn minuend
  [diff-tree]
    (if (one? diff-tree) one
      (diff-first diff-tree)))

(defn subtrahend
  [diff-tree]
    (if (one? diff-tree)
      (diff one one)
      (diff-second diff-tree)))

; Here are the four operations we have to implement. Note that is-zero?
; explicitly converts the diff-tree to an integer and compares it with 0.
; Since we know how successor and predecessor work, there is probably a more
; interesting way to check (without conversion). #TODO: Find the way to do that
(def zero (diff one one))

(defn is-zero?
  [n]
  (defn to-int
    [n]
    (if (one? n) 1
      (- (to-int (minuend n)) (to-int (subtrahend n))))
    (zero? (to-int n))))

(defn successor
  [n]
  (diff (minuend n) (diff (subtrahend n) one)))

(defn predecessor
  [n]
  (diff n one))

;; 3. diff-tree-plus
(defn diff-tree-plus
  [diff-tree-1 diff-tree-2]
    (diff diff-tree-1
      (diff (subtrahend diff-tree-2)
        (minuend diff-tree-2))))

;; more functions
(defn minus
  {:doc "returns the difference between two diff-tree representations"}
  [n]
  (diff zero n))

(defn plus
  {:doc "adds two integers in diff-tree representation"}
  [x y]
  (diff x (minus y)))

(defn negate
  {:doc ""}
  [n]
  (if (= (one n))
    (diff zero one)
    (diff (rest (rest n)) (rest n))))

;; successor and predecessor without using minuend and subtrahend
(defn successor
  {:doc "returns the successor of an integer in diff-tree representation"}
  [n]
  (plus n (one)))

(defn predecessor
  {:doc "returns the predecessor of an integer in diff-tree representation"}
  [n]
  (diff n (one)))

;;
;; unit-tests
;;
(is (= one '(one)))
(is (= (diff one one) '(diff (one) (one))))
(is (= (diff (diff one one) (diff one one)) '(diff (diff (one) (one)) (diff (one) (one)))))
