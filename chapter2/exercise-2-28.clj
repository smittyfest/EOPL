;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.28[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Write an unparser that converts the abstract syntax
;; of an LcExp into a String that matches the grammar:
;;
;; LcExp ::= Identifier [var-exp (var)]
;;       ::= (lambda (Identifier) LcExp) [lambda-exp (bound-var body)]
;;       ::= (LcExp LcExp) [app-exp (rator rand)]
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

;; Define cases expression to be used in unparser
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

(define-datatype lc-exp lc-exp?
  (var-exp
    (var identifier?))
  (lambda-exp
    (bound-var identifier?)
    (body lc-exp?))
  (app-exp
    (rator lc-exp?)
    (rand lc-exp?)))

(defn symbol->string
  {:doc "Converts a Symbol to a String"}
  [sym]
  (name :sym))

(defn unparse-lc-exp
  {:doc "Unparses an abstract syntax for a particular Lambda Calculus expression into a concrete syntax"}
  [exp]
  (cases lc-exp exp
    (var-exp (var)
      (symbol->string var))
    (lambda-exp (bound-var body)
      (str "(lambda (" (symbol->string bound-var) ") "
                      (unparse-lc-exp body) ")"))
    (app-exp (rator rand)
      (str "(" (unparse-lc-exp rator) " "
                      (unparse-lc-exp rand) ")"))))
  
  ;; unit-tests
