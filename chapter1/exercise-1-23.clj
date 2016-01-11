;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.23[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))

(declare track-index)

(defn list-index
  {:doc "Returns the index of the first element satisfying a predicate or false"}
  [pred xs]
  (if (empty? xs) false
    (track-index pred xs 0)))

(defn track-index
  {:doc "Uses an accumulator to track the current index"}
  [pred xs accu]
  (if (empty? xs) false
    (if (pred (first xs)) accu (track-index pred (rest xs) (inc accu)))))
;;
;; unit-tests
;;
(is (= (list-index number? ()) false))
(is (= (list-index number? '(1 a b)) 0))
(is (= (list-index number? '(a 2 (1 3) b 7)) 1))
(is (= (list-index symbol? '(a (b c) 17 f)) 0))
(is (= (list-index symbol? '(1 2 (a b) 3)) false))
(is (= (list-index symbol? '(1 2 (a b) 3 4 (a z) 5 6 n)) 8))
