;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.20[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn count-occurrences
  {:doc "Return the number of occurrences of x in xs"}
  [x xs]
  (reduce (fn [accu i]
    (if (symbol? i)
      (if (= x i) (inc accu) accu)
      (+ accu (count-occurrences x i)))) 0 xs))
;;
;; unit-tests
;;
(is (= (count-occurrences 'a ()) 0))
(is (= (count-occurrences 'a '(a b)) 1))
(is (= (count-occurrences 'a '(a a a a a)) 5))
(is (= (count-occurrences 'a '((f a) y (((a z) a)))) 3))
(is (= (count-occurrences 'a '((f a) y (((a z) () a)))) 3))
(is (= (count-occurrences 'a '((f x) y (((x z) x)))) 0))
