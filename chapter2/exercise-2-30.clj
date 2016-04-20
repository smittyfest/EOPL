;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.30[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; The procedure parse-expression as defined above
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
  [exp msg]
  (println 'parse-expression (str "Syntax error: ~s is " msg) exp))

(defn parse-expression
  [exp]
  (cond
    (symbol? exp) (var-exp exp)
    (pair? exp)
      (if (= 'lambda (first exp))
        (cond
          (not (pair? (rest exp)))
            (report-invalid-concrete-syntax exp "missing a list of bound variables")
          (not (list? identifier?) (first (rest exp)))
            (report-invalid-concrete-syntax exp "not a list of bound variables")
          (not (pair? (rest (rest exp))))
            (report-invalid-concrete-syntax exp "missing a body")
          :else
            (lambda-exp (first (rest exp)
              (parse-expression (first (rest (rest exp)))))))
        (if (not (list? exp))
          (report-invalid-concrete-syntax exp "not an application expression")
          (app-exp (parse-expression (first exp))
            (map parse-expression (rest exp)))))
    :else (report-invalid-concrete-syntax exp "not a lambda expression")))

;; unit-tests
