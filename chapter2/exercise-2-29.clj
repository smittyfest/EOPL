;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.29[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Where a Kleene Star or Kleene Plus is used in concrete syntax, it
;; is most convenient to use a list of associated subtrees when constructing
;; an abstract syntax tree. For example, if the grammar for lambda-calculus
;; expressions had been:
;;
;; LcExp ::= Identifier
;;           |var-exp (var)|
;;       ::= (lambda ({Identifier}*) LcExp)
;;           |lambda-exp (bound-vars body)|
;;       ::= (LcExp {LcExp}*)
;;           |app-exp (rator rands)|
;;
;; then the predicate for the bound-vars field could be (list-of identifier?),
;; and the predicate for the rands field could be (list-of lc-exp?).
;; Write a define-datatype and a parser for this grammar that works in this way.
;;
(ns eopl.ch02 (:use clojure.test))

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

 (defn identifier?
   [x]
   (and (symbol? x) (not= 'lambda x)))

;; type definition for lambda calculus expressions
 (define-datatype lc-exp lc-exp?
 (var-exp
  (var identifier?))
 (lambda-exp
  (bound-vars (list identifier?))
  (body lc-exp?))
 (app-exp
  (rator lc-exp?)
  (rands (list lc-exp?))))

(defn pair?
  [exp]
  (and (first exp) (second exp)))

(defn report-invalid-concrete-syntax
  [exp]
  (println 'parse-expression "Syntax error: ~s" exp))

(defn parse-expression
  [exp]
  (cond
    (symbol? exp) (var-exp exp)
    (pair? exp)
      (if (= 'lambda (first exp))
        (lambda-exp (first (rest exp)
          (parse-expression (first (rest (rest exp)))))
        (app-exp (parse-expression (first exp))
          (map parse-expression (rest exp)))))
    :else (report-invalid-concrete-syntax exp)))

;; unit-tests
