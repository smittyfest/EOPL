;; ------------------------------------------------------------------------------
;; Exercise 1.8[*]
;;
(ns eopl.ch01 (:use clojure.test))
(defn remove-first
  {:doc "Remove first occurrence of s from the list xs"}
  [xs s]
  (if (empty? xs)
  '()
  (loop [tmp xs]
    (if (= (s (first tmp)))
      (rest tmp)
    (recur (cons (first tmp) (remove-first s (rest tmp))))))))

;;
;; run some unit-tests
;;
(is (= (remove-first '(a b c) 'a) '(b c)))
