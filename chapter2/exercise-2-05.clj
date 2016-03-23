;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.5[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
;; association-list implementation of environments
;;
(ns eopl.ch02 (:use clojure.test))

;; Vector Representation of Association-List
(defn empty-env
  {:doc "Representation of an empty environment"}
  []
    [])

(defn extend-env
  {:doc "Create a new binding in an environment"}
  [var val env]
  (conj env (vector var val)))

(defn apply-env
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (if (sequential? env)
    (let [[var val] (peek env)]
      (if (= search-var var) val
      (recur search-var (pop env))))))
;;
;; unit-tests
;;
(is (= (empty-env) []))
(is (= (extend-env 'y \y (empty-env)) '[[y \y]]))
(is (= (extend-env 'z \z (extend-env 'y \y (empty-env))) '[[y \y] [z \z]]))
(is (= (extend-env 'H "Fire Giant" (extend-env 'G "Ghost" (extend-env 'M "Mind Flayer" (empty-env)))) '[[M "Mind Flayer"] [G "Ghost"] [H "Fire Giant"]]))
(is (= (apply-env 'y (extend-env 'y \y (empty-env))) \y))
(let [env (extend-env 'z \z (extend-env 'y \y (empty-env)))]
(is (= (apply-env 'z env) \z))
(is (= (apply-env 'y env) \y)))
(is (= (apply-env 'y '([z \z] [y \y])) \y))
(is (= (apply-env 'h (extend-env 'H "Fire Giant" (extend-env 'T "Troll" (extend-env 'h "Mind Flayer" (empty-env))))) "Mind Flayer"))
