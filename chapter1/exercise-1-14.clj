;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.14[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;;
(ns eopl.ch01 (:use clojure.test))
(declare number-elements-from)
(defn number-elements
  {:doc "takes a list (v0 v1 ..) and returns a list ((0 v0) (1 v1) ..)"}
  [lst]
  (number-elements-from lst 0))

(defn number-elements-from
  {:doc "takes a list (v0 v1 ..) and returns a list ((Int v0) (Int v1) ..)"}
  [lst n]
    (if (empty? lst)
      ()
    (cons
      (list n (first lst))
      (number-elements-from (rest lst) (+ n 1)))))

(defn partial-vector-sum
  {:doc "sum the elements in a slice of a vector of Ints"}
  [vec n]
  (if (zero? n)
      (nth vec 0)
      (+ (nth vec n) (partial-vector-sum vec (- n 1)))))

(defn vector-sum
  {:doc "sum all of the elements in a vector of Ints"}
  [vec]
  (let [n (count vec)]
  (if (zero? n)
    0
    (partial-vector-sum vec (- n 1)))))
;;
;; unit-tests
;;
(is (= (number-elements ()) ()))
(is (= (number-elements '('a 'b 'c)) '((0 'a) (1 'b) (2 'c))))
(is (= (vector-sum []) 0))
(is (= (vector-sum [1 2 3]) 6))
(is (= (vector-sum [25 25 25 25]) 100))
