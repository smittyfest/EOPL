;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.20[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(declare counter)
(defn count-occurrences
  {:doc "Return the number of occurrences of x in xs"}
  [x xs]
  (if (empty? xs) 0
    (counter 0 x xs)))

(defn- counter
  {:doc "Accumulates count totals for x in xs"}
  [accu x xs]
  (cond
    (empty? xs) accu
    (symbol? (first xs))
      (if (= x (first xs))
        (counter (inc accu) x (rest xs))
        (counter accu x (rest xs)))
    :else (counter accu x (first xs))))
;;
;; unit-tests
;;
(is (= (count-occurrences 'a ()) 0))
(is (= (count-occurrences 'a '(a b)) 1))
(is (= (count-occurrences 'a '(a a a a a)) 5))
(is (= (count-occurrences 'a '((f a) y (((a z) a)))) 3))
(is (= (count-occurrences 'a '((f a) y (((a z) () a)))) 3))
(is (= (count-occurrences 'a '((f x) y (((x z) x)))) 0))
