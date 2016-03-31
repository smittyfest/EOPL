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

(defn unparse-lcexp
  {:doc "Unparses an abstract syntax for a particular Lambda Calculus expression into a concrete syntax"}
  [exp]
  (cases lcexp exp
    (var-exp (var) 'Identifier)
    (lambda-exp (bound-var body))
      (list 'lambda '(Identifier) (unparse-lcexp body))
    (app-exp (rator rand) (list (unparse-lcexp rator) (unparse-lcexp rand)))))
  
  ;; unit-tests
