;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.18[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; We usually represent a sequence of values as a list. In this representation,
;; it is easy to move from one element in a sequence to the next, but it is hard
;; to move from one element to the preceding one without the help of context arguments.
;; Implement non-empty, bidirectional sequences of integers, as suggested by the grammar:
;;
;; NodeInSequence ::= (Int List[Int] List[Int])
;;
;; The first list of numbers is the elements of the sequence preceding the current one,
;; in reverse order, and the second list is the elements of the sequence after the current one.
;; For example, (6 (5 4 3 2 1) (7 8 9)) represents the list (1 2 3 4 5 6 7 8 9), with the focus
;; on the element 6.
;; In this representation, 
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
