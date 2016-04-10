;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.31[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; Sometimes it is useful to specify a concrete syntax as a sequence
;; of symbols and integers, surrounded by parentheses.
;; For example, one might define the set of prefix-lists by:
;;
;; Prefix-List ::= (Prefix-Exp)
;; Prefix-Exp  ::= Int
;;             ::= - Prefix-Exp Prefix-Exp
;;
;; so that (- - 3 2 - 4 - 12 7) is a legal prefix-list. This is sometimes called
;; Polish Prefix Notation, after its inventor, Jan Lukasiewicz.
;;
;; Write a parser to convert a prefix-list to the abstract syntax:
;;
;; (define-datatype prefix-exp prefix-exp?
;;   (const-exp
;;     (num integer?))
;;   (diff-exp
;;     (operand1 prefix-exp?)
;;     (operand2 prefix-exp?)))
;;
;; so that the example above produces the same abstract syntax tree as the sequence
;; of constructors:
;;
;; (diff-exp
;;   (diff-exp
;;     (const-exp 3)
;;     (const-exp 2))
;;   (deff-exp
;;     (const-exp 4)
;;     (diff-exp
;;       (const-exp 12)
;;       (const-exp 7))))
;;
;; As a hint, consider writing a procedure that takes a list and produces a prefix-exp
;; and the list of leftover list elements.
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

(def prefix-exp? list?)

(defn const-exp
  [x]
  (list 'const-exp x))

(defn diff-exp
  [x y]
  (list 'diff-exp x y))

(defn prefix-exp?
  [exp]
  (or (prefix-exp? exp) number? exp))

(defn exp
  [x]
  (if (number? x) (const-exp x)
    x))
  
(defn parse-prefix
  ([exp]
     (if (prefix-exp? (first exp))
       (first exp)
       (parse-prefix (list (second exp) (first exp)) (rest (rest exp)))))
  ([left right]
     (let [[f s & x] left]
       (if (and (prefix? f) (prefix? s))
         (parse-prefix (concat (reverse (rest x))
                               (list* (diff-exp
                                       (exp s)
                                       (exp f)) right)))
         (recur (cons (first right) left) (rest right))))))

(deftest parse-prefix-test
  (is (= (parse-prefix '(- - 3 2 - 4 - 12 7))
    '(diff-exp
      (diff-exp
        (const-exp 3)
        (const-exp 2))
      (diff-exp
        (const-exp 4)
        (diff-exp
         (const-exp 12)
         (const-exp 7)))))))
(run-tests)
