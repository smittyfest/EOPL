;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.11[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; A naive implementation of extend-env* from exercise 2.10 requires time proportional
;; to k to run. It is possible to represent environments so that extend-env* requires only
;; constant time: represent the empty environment by the empty list, and represent
;; a non-empty environment by the data structure:
;;  
;;                +--+--+
;;                |  |  | -> saved-environment
;;                +--+--+
;;                 |
;;                 v
;;                +--+--+
;;  saved vars <- |  |  | -> saved vals
;;                +--+--+
;;
;; Such an environment might look like:
;;
;;                +--+--+                       +--+--+                     +--+--+
;;                |  |  | --------------------> |  |  | ------------------> |  |  | ----------> rest of saved environment
;;                +--+--+                       +--+--+                     +--+--+
;;                 |                             |                           |
;;                 v                             v                           v
;;                +--+--+                       +--+--+                     +--+--+
;;     (a b c) <- |  |  | -> (1 2 3)   (x z) <- |  |  | -> (4 5)   (w y) <- |  |  | -> (88 99)
;;                +--+--+                       +--+--+                     +--+--+
;;
;; The environment is represented as a list of pairs;
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  {:doc "Representation of an empty environment"}
  []
    ())

(defn empty-env?
  {:doc "Returns true if given environment is empty"}
  [env]
  (= env (empty-env)))

(defn extend-env
  {:doc "Add a binding to an environment"}
  [var val env]
  (cons [`(~var) `(~val)] env))

(defn extend-env*
  {:doc "Create a series of new bindings in an environment"}
  [vars vals env]
  (cons [vars vals] env))

(defn apply-env
  {:doc "Returns the value associated with a variable in a particular environment"}
  [search-var env]
  (if (empty-env? env) nil
  (let [pair (first env) v (find (zipmap (first pair) (second pair)) search-var)]
    (if v (second v)
      (recur search-var (rest env))))))

(defn has-binding?
  {:doc "Returns true if a variable has a value in a given environment, false otherwise"}
  [search-var env]
  (not= (apply-env search-var env) nil))
;;
;; unit-tests
;;
(is (= (empty-env) ()))
(is (= (empty-env? (empty-env)) true))
(is (= (extend-env 'a 1 ()) '([(a) (1)])))
(is (= (extend-env 'b 2 '([(a) (1)])) '([(b) (2)] [(a) (1)])))
(is (= (extend-env* '(x y z) '(10 11 12) '([b 2] [a 1])) '([(x y z) (10 11 12)] [b 2] [a 1])))
(is (= (extend-env* '(a b c) '(1 2 3) ()) '([(a b c) (1 2 3)])))
(is (= (extend-env* '(d e f) '(4 5 6) '([(a b c) (1 2 3)])) '([(d e f) (4 5 6)] [(a b c) (1 2 3)])))
(is (= (extend-env* '(g h i) '(7 8 9) '([(d e f) (4 5 6)] [(a b c) (1 2 3)])) '([(g h i) (7 8 9)] [(d e f) (4 5 6)] [(a b c) (1 2 3)])))
(is (= (apply-env 'a ()) nil))
(is (= (apply-env 'a '([(a b c) (1 2 3)])) 1))
(is (= (apply-env 'b '([(a b c) (1 2 3)])) 2))
(is (= (apply-env 'c '([(a b c) (1 2 3)])) 3))
(is (= (apply-env 'a '([(x y z) (10 11 12)] [(b) (2)] [(a) (1)])) 1))
(is (= (has-binding? 'a ()) false))
(is (= (has-binding? 'a '([(x y z) (1 2 3)])) false))
(is (= (has-binding? 'z '([(a b c d) (1 2 3 4)] [(x y z w) (14 15 16 17)])) true))
