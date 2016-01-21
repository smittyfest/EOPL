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
(is (= (is-zero? ()) true))
(is (= (successor ()) '(1)))
(is (= (predecessor '(1)) ()))
(is (= (successor (successor (successor ()))) '(3)))
