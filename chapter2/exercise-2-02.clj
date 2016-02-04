;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.2[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

;; unary representation
(def zero ())
(defn is-zero? [n] (empty? n))
(defn succ [n] (cons true n))
(defn pred [n] (rest n))
;; The unary representation is a simple implementation since it represents integers as a list of boolean 'true' values.
;; This implementation has constant access time for any integer, but the space complexity increases linearly as n
;; becomes large, resulting in poor memory-efficiency for large values of n.

;; Clojure representation
(def zero 0)
(defn is-zero? [n] (zero? n))
(defn succ [n] (+ n 1))
(defn pred [n] (- n 1))
;; Creates a new abstraction layer that delegates the implementation to Clojure.
;; This will help clients remain unaffected if we decide to change the representation of the
;; data specification for any reason.
;; The performance of this implementation is similar to that of Clojure and the JVM.

;; Bigint representation
(def BASE 10)
(def zero ()) ;; returns bigint representation of zero
(defn is-zero?
  {:doc "returns true if n is equal to the bigint representation of zero"}
  [n]
  (empty? n))
(defn successor
  {:doc "returns the successor to n in bigint representation"}
  [n]
  (cond
    (is-zero? n) '(1)
    (= (inc (first n)) BASE) (cons 0 (successor (rest n)))
    :else (cons (+ (first n) 1) (rest n))))
(defn predecessor
  {:doc "returns the predecessor to n in bigint representation"}
  [n]
  (cond
    (zero? (first n)) (cons (- BASE 1) (predecessor (rest n)))
    (and (= (first n) 1) (is-zero? (rest n))) ()
    :else (cons (- (first n) 1) (rest n))))
;; The bigint representation is more space-efficient than both the unary representaion
;; and the Clojure-delegated representation for large integers.
;; The time-complexity is pretty hideous with this implementation, far worse than the others.
;; This would be an acceptable implementation for applications that need to store huge integers
;; but don't necessarily need to compute them in a timely manner.
