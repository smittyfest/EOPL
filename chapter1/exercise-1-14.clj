;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.14[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; The Induction hypothesis IH(n) states that partial-vector-sum correctly returns the sum of the elements of
;; a Vector[Int].
;;
;; 1. A Vector[Int] of length 0 has no elements, therefore IH(0) holds for the trivial case.
;;
;; 2. Given that IH(k) holds, we wish to show that IH(k+1) holds also.
;;    Since 0 <= n < lenght(vec), we know that k+1 is not equal to 0
;;    IH(k)   = v0 + v1 + ... + vk.
;;    IH(k+1) = IH(k) + vk+1, 
;;    Since vk+1 is the value of v[k], IH(k) holds as well.
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
