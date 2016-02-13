;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.6[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Map, Set, and Tree representations of environments.
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  {:doc "Map Representation of an empty environment"}
  []
   {})

(defn extend-env
  {:doc "Create a new binding in an environment"}
  [var val env]
  (assoc env var val))

(defn apply-env
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (get env search-var))

(defn empty-env-set
  {:doc ""}
  []
    #{})

(defn extend-env-set
  {:doc ""}
  [var val env]
  (conj env (vector var val)))

(defn apply-env-set
  {:doc ""}
  [search-var env]
  (if (sequential? (seq env))
    (let [[var val] (first env)]
      (if (= search-var var) val
      (recur search-var (rest env))))))
;;
;; unit-tests
;;
(is (= (empty-env) {}))
(is (= (empty-env-set) #{}))

(is (= (extend-env :a 1 (empty-env)) {:a 1}))
(is (= (extend-env 'z \z (empty-env)) {'z \z}))
(is (= (extend-env :c 3 (extend-env :b 2 (extend-env :a 1 (empty-env)))) {:a 1, :b 2, :c 3}))
(is (= (extend-env-set :a 1 (empty-env-set)) #{[:a 1]}))
(is (= (extend-env-set :c 3 (extend-env-set :b 2 (extend-env-set :a 1 (empty-env-set)))) #{[:a 1] [:b 2] [:c 3]}))
(is (= (apply-env :b (extend-env :c 3 (extend-env :b 2 (extend-env :a 1 (empty-env))))) 2))
(is (= (apply-env :b '{:a 1, :b 2, :c 3}) 2))
(is (= (apply-env :G (extend-env :H "Frost Giant" (extend-env :i "Tengu" (extend-env :G "Ghost-Tengu-FrostGiant" (empty-env))))) "Ghost-Tengu-FrostGiant"))
(is (= (apply-env-set :b (extend-env-set :c 3 (extend-env-set :b 2 (extend-env-set :a 1 (empty-env-set))))) 2))
(is (= (apply-env-set :b '#{[:a 1] [:b 2] [:c 3]}) 2))
;; internal representations are interchangeable
(is (= (apply-env-set :G (extend-env :H "Frost Giant" (extend-env :i "Tengu" (extend-env :G "Ghost-Tengu-FrostGiant" (empty-env))))) "Ghost-Tengu-FrostGiant"))
