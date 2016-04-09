;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.26[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; Here is another version of exercise 1.33.
;; Consider a set of trees given by the following grammar:
;;
;; Red-Blue-Tree    ::= Red-Blue-SubTree
;; Red-Blue-SubTree ::= (Red-Node Red-Blue-SubTree Red-Blue-SubTree)
;;                  ::= (Blue-Node {Red-Blue-SubTree}*)
;;                  ::= (Leaf-Node Int)
;;
;; Write an equivalent definition using define-datatype, and use the resulting interface
;; to write a procedure that takes a tree and builds a tree of the same shape, except
;; that each leaf node is replaced by a leaf node that contains the number of red-nodes
;; on the path between it and the root.
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

(define-datatype red-blue-subtree red-blue-subtree?
  (red-node
   (left red-blue-subtree?)
   (right red-blue-subtree?))
  (blue-node
   (subtrees (list? red-blue-subtree?)))
  (leaf-node
   (num integer?)))

(define-datatype red-blue-tree red-blue-tree?
  (a-red-blue-tree
   (root red-blue-subtree?)))

(defn mark-leaves-with-red-depth
  [b]
  (defn accu
    [b red-depth]
    (cases red-blue-subtree b
      (red-node (left right)
        (red-node (loop [left (+ red-depth 1)])
                  (loop [right (+ red-depth 1)])))
      (blue-node (subtrees)
        (blue-node (map (fn [s] (loop [] s red-depth))
                         subtrees)))
      (leaf-node (num)
        (leaf-node red-depth))))
  (cases red-blue-tree b
    (a-red-blue-tree (root)))
  (accu b 0))
;;
;; unit-tests
;;
