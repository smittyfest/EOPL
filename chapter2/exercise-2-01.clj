;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.1[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

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

;; more functions
(defn plus
  {:doc "adds two integers"}
  [x y]
  (if (is-zero? x) y
    (successor (plus (predecessor x) y))))

(defn multiply
  {:doc "multiplies two integers"}
  [x y]
  (cond
    (or (is-zero? x) (is-zero? y)) zero
    (is-zero? (predecessor x)) y
    :else (plus y (multiply (predecessor x) y))))  
 
;; factorial
(defn fact
  {:doc "returns the factorial of n"}
  [n]
  
;; constants
(def one (successor ()))
(def two (successor (successor ())))
(def three (successor (successor (successor ()))))
(def four (successor (successor (successor (successor ())))))
(def five (successor (successor (successor (successor (successor ()))))))
(def six (successor (successor (successor (successor (successor (successor ())))))))
(def seven (successor (successor (successor (successor (successor (successor (successor ()))))))))
(def eight (successor (successor (successor (successor (successor (successor (successor (successor ())))))))))
(def nine (successor (successor (successor (successor (successor (successor (successor (successor (successor ()))))))))))
(def ten (successor (successor (successor (successor (successor (successor (successor (successor (successor (successor ())))))))))))
;;
;; unit-tests
;;
(is (= ('zero ())))
(is (= (is-zero? ()) true))
(is (= ('one '(1))))
(is (= ('two '(2))))
(is (= ('three '(3))))
(is (= ('four '(4))))
(is (= ('five '(5))))
(is (= ('six '(6))))
(is (= ('seven '(7))))
(is (= ('eight '(8))))
(is (= ('nine '(9))))
(is (= ('ten '(10))))
(is (= (predecessor '(11)) '(10)))
(is (= (predecessor '(10)) '(9)))
(is (= (predecessor '(9)) '(8)))
(is (= (predecessor '(8)) '(7)))
(is (= (predecessor '(7)) '(6)))
(is (= (predecessor '(6)) '(5)))
(is (= (predecessor '(5)) '(4)))
(is (= (predecessor '(4)) '(3)))
(is (= (predecessor '(3)) '(2)))
(is (= (predecessor '(2)) '(1)))
(is (= (predecessor '(1)) ()))
