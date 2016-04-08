;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.18[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; We usually represent a sequence of values as a list. In this representation,
;; it is easy to move from one element in a sequence to the next, but it is hard
;; to move from one element to the preceding one without the help of context arguments.
;;
;; Implement non-empty, bidirectional sequences of integers, as suggested by the grammar:
;;
;; NodeInSequence ::= (Int List[Int] List[Int])
;;
(ns eopl.ch02 (:use clojure.test))

(defn number->sequence
  {:doc ""}
  [n]
  (make-sequence n () ()))

(defn make-sequence
  {:doc ""}
  [current-element left-part right-part]
  (list current-element left-part right-part))

(defn current-element [exp] (first (seq exp)))
(defn left-part [exp] (first (rest (seq exp))))
(defn right-part [exp] (first (rest (rest (seq exp)))))

(defn move-to-left
  {:doc ""}
  [sequence]
  (if (at-left-end? sequence)
    (report-moving-too-far 'move-to-left)
    (make-sequence
      (first (left-part sequence))
      (rest (left-part sequence))
      (cons (current-element sequence) (right-part sequence)))))

(defn move-to-right
  {:doc ""}
  [sequence]
  (if (at-right-end? sequence)
    (report-moving-too-far 'move-to-right)
    (make-sequence (first (right-part sequence))
      (cons (current-element sequence) (left-part sequence))
      (rest (right-part sequence)))))

(defn insert-to-left
  {:doc ""}
  [n sequence]
  (make-sequence (current-element sequence)
    (cons n (left-part sequence))
    (right-part sequence)))

(defn insert-to-right
  [n sequence]
  (make-sequence (current-element sequence)
    (left-part sequence)
    (cons n (right-part sequence))))

(defn at-left-end? [sequence] (nil? (left-part sequence)))
(defn at-right-end? [sequence] (nil? (right-part sequence)))

(defn report-moving-too-far
  [func]
  (println func "Moved too far"))
;;
;; unit-tests
;;
