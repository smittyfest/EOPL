;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.9[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(defn empty-env
  {:doc "Representation of an empty environment"}
  []
    [])

(defn empty-env?
  {:doc "Returns true if given environment is empty"}
  [env]
  (= env (empty-env)))

(defn extend-env
  {:doc "Create a new binding in an environment"}
  [var val env]
  (conj env (vector var val)))

(defn apply-env
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (cond
    (= env []) nil
    (sequential? env) 
      (let [[var val] (peek env)]
        (if (= search-var var) val
        (recur search-var (pop env))))))

;; helper-function for has-binding?
(def not-nil? (complement nil?))

(defn has-binding?
  {:doc "Returns true if there is a binding for var in environment env"}
  [var env]
  (not-nil? (apply-env var env)))
;;
;; unit-tests
;;
(is (= (empty-env) []))
;;(is (= (empty-env) ()))
(is (= (extend-env 'y \y (empty-env)) '[[y \y]]))
;;(is (= (extend-env 'y \y (empty-env)) '([y \y])))
(is (= (extend-env 'z \z (extend-env 'y \y (empty-env))) '[[y \y] [z \z]]))
;;(is (= (extend-env 'z \z (extend-env 'y \y (empty-env)))) '[[y \y] [z \z]]))
(is (= (extend-env 'H "Fire Giant" (extend-env 'G "Ghost" (extend-env 'M "Mind Flayer" (empty-env)))) '[[M "Mind Flayer"] [G "Ghost"] [H "Fire Giant"]]))
;;(is (= (extend-env 'H "Fire Giant" (extend-env 'T "Troll" (extend-env 'h "Mind Flayer" (empty-env)))) '([H "Fire Giant"] [T "Troll"] [h "Mind Flayer"])))
(is (= (apply-env 'y (extend-env 'y \y (empty-env))) \y))
(let [env (extend-env 'z \z (extend-env 'y \y (empty-env)))]
(is (= (apply-env 'z env) \z))
(is (= (apply-env 'y env) \y)))
(is (= (apply-env 'y '([z \z] [y \y])) \y))
(is (= (apply-env 'h (extend-env 'H "Fire Giant" (extend-env 'T "Troll" (extend-env 'h "Mind Flayer" (empty-env))))) "Mind Flayer"))
(is (= (empty-env? (empty-env)) true))
(is (= (empty-env? []) true))
(is (= (empty-env? '[[:a \z]]) false))
(is (= (has-binding? :a '[[:a \z]]) true))
(is (= (has-binding? :a (extend-env :princess :toadstool (extend-env :hammer :brothers (empty-env)))) false))
