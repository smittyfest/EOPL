;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.21[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Implement the data type of environments, as in section 2.2.2, using
;; "define-datatype" ("deftype" in Clojure). Then include "has-binding?" of exercise 2.9.
;;
(ns eopl.ch02 (:use clojure.test))

;; The deftype form of Clojure is not quite the same construct
;; as 'define-datatype'
(defprotocol IEnvironment
  (empty-env [this])
  (extend-env [this var val])
  (apply-env [this var env]))

(deftype Environment []
  IEnvironment
  (empty-env [this] ())
  (extend-env [this var val] (prn "woof!"))
  (apply-env [this val env] (prn "meow!")))

;; In order to implement 'define-datatype', we need to utilize Clojure macros
(defmacro data-type-predicate [type-name type-predicate-name]
  `(defn ~type-predicate-name [~'variant]
     (= (:type ~'variant) '~type-name)))

(defmacro data-type-variant [variant type-name]
  (let [variant-name (first variant)
        variant-spec (rest variant)
        variant-field-names (map first variant-spec)
        variant-args (mapcat #(reverse (take (if (= (second %1) '&) 2 1) %1)) variant-spec)
        variant-field-predicates (map last variant-spec)]
    `(do
       (defn ~(symbol (str variant-name "?")) [data#]
         (and (= (get data# :type) '~type-name)
              (= (get data# :variant) '~variant-name)))
       (defn ~variant-name [~@variant-args]
         {:pre [~@(map list variant-field-predicates variant-field-names)]}
         {:type '~type-name
          :variant '~variant-name
          :values (array-map
                   ~@(mapcat #(list (keyword %1) %1) variant-field-names))}))))

(defmacro define-datatype [type-name type-predicate-name & variants]
  `(do
     (data-type-predicate ~type-name ~type-predicate-name)
     ~@(map (fn [v] `(data-type-variant ~v ~type-name)) variants)))

(defmacro cases [type-name expression & clauses]
  (let [variant (gensym)]
    `(let [~variant ~expression]
       (if (not= (:type ~variant) '~type-name)
         (throw (Exception. (str "invalid type expected " '~type-name " found '" (:type ~variant) "'"))))
       (cond ~@(mapcat (fn [clause]
                         (let [variant-name (first clause)]
                           (if (= variant-name 'else)
                             `(:else ~@(rest clause))
                             (let [[_ field-names & consequent] clause]
                               `((= (:variant ~variant) '~variant-name)
                                 (apply
                                  (fn [~@field-names]
                                    ~@consequent)
                                  (vals (:values ~variant))))))))
                       clauses)))))

(defn data-value [data field-name]
  (get (get data :values) field-name))

;;
;; unit-tests
;;
