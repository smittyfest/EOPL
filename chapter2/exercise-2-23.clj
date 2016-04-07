;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.23[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; The definition of lc-exp ignores the condition in definition 1.1.8
;; that says "Identifier is any symbol other than lambda." Modify the definition of
;; "identifier?" to capture this condition. As a hint, remember that any predicate
;; can be used in "define-datatype" ("deftype" in Clojure), even ones you define.
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
  (and
    (symbol? x)
    (not= 'lambda x)))

(define-datatype lc-exp lc-exp?
  (var-exp
   (var identifier?))
  (lambda-exp
   (bound-var identifier?)
   (body lc-exp?))
  (app-exp
   (rator lc-exp?)
   (rand lc-exp?)))

(defn occurs-free? [search-var exp]
  (cases lc-exp exp
         (var-exp (var) (= var search-var))
         (lambda-exp (bound-var body)
                     (and (not (= search-var bound-var))
                          (occurs-free? search-var body)))
         (app-exp (rator rand)
                  (or (occurs-free? search-var rator)
                      (occurs-free? search-var rand)))))
;;
;; unit-tests
;;
(deftest occurs-free-test
  (is (occurs-free? 'x (var-exp 'x)))
  (is (not (occurs-free? 'x (var-exp 'y))))
  (is (not (occurs-free? 'x (lambda-exp 'x (app-exp (var-exp 'x) (var-exp 'y))))))
  (is (occurs-free? 'x (lambda-exp 'y (app-exp (var-exp 'x) (var-exp 'y)))))
  (is (occurs-free? 'x (app-exp (lambda-exp 'x (var-exp 'x)) (app-exp (var-exp 'x) (var-exp 'y)))))
  (is (occurs-free? 'x (lambda-exp 'y (lambda-exp 'z (app-exp (var-exp 'x) (app-exp (var-exp 'y) (var-exp 'z)))))))
  (is (thrown? AssertionError (var-exp 'lambda))))

(run-tests)
