;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.7[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
;; Print out error msgs for missing keys or poorly-defined environments
;;
(ns eopl.ch02 (:use clojure.test))

(defn apply-env
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (cond
    (= env []) (println "No binding found for " + search-var)
    (sequential? env)
      (let [[var val] (peek env)]
        (if (= search-var var) val
        (recur search-var (pop env))))
    :else (println "No binding found for environment " + env)))
;;
;; unit-tests
;;
(is (= (apply-env :a '[[:b \b] [:a \a]]) \a))
(apply-env :a '[[:b \b]])
(apply-env :a '{:a \a})
