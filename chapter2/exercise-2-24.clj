;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.24[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Here is a definition of binary trees using define-datatype:
;; 
;; (define-datatype bintree bintree?
;; (leaf-node
;; (num integer?))
;; (interior-node
;; (key symbol?)
;; (left bintree?)
;; (right bintree?)))
;;
;; Implement a bintree-to-list procedure for binary trees, so that:
;;
;; (bintree-tolist (interior-node ’a (leaf-node 3) (leaf-node 4)))
;;
;; returns the list:
;;
;; (interior-node
;; a
;; (leaf-node 3)
;; (leaf-node 4))
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

(define-datatype bintree bintree?
  (leaf-node
   (num integer?))
  (interior-node
   (key symbol?)
   (left bintree?)
   (right bintree?)))

(defn bintree-to-list [bt]
  (cases bintree bt
         (leaf-node (num) `(~'leaf-node ~num))
         (interior-node (key left right)
                        (list 'interior-node
                              key
                              (bintree-to-list left)
                              (bintree-to-list right)))))
;;
;; unit-tests
;;
(deftest bintree-to-list-test
  (is (= (bintree-to-list (interior-node 'a (leaf-node 3) (leaf-node 4)))
         '(interior-node
           a
           (leaf-node 3)
           (leaf-node 4)))))

(run-tests)
