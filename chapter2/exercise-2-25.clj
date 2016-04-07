;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.25[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; Use cases to write max-interior, which takes a binary tree of
;; integers (as in the preceding exercise) with at least one interior node and returns the
;; symbol associated with an interior node with a maximal leaf sum.
;;
;; > (define tree-1
;; (interior-node ’foo (leaf-node 2) (leaf-node 3)))
;;
;; > (define tree-2
;; (interior-node ’bar (leaf-node -1) tree-1))
;;
;; > (define tree-3
;; (interior-node ’baz tree-2 (leaf-node 1)))
;;
;; > (max-interior tree-2)
;; foo
;;
;; > (max-interior tree-3)
;; baz
;;
;; The last invocation of max-interior might also have returned foo, since both the
;; foo and baz nodes have a leaf sum of 5.
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
 
(define-datatype best best?
  (a-best
   (max-best integer?)
   (key-best symbol?)))

(define-datatype result result?
  (a-result
   (sum-result integer?)
   (best-result best?)))

(defn sum-result
  [r]
  (cases result r
    (a-result (sum best)
      sum)))

(defn best-result
  [r]
  (cases result r
    (a-result (sum best)
      best)))

(defn max-best
  [b]
  (cases best b
    (a-best (max key)
      max)))

(defn key-best
  [b]
  (cases best b
    (a-best (max key)
      key)))

(defn choose-maximum
  [best1 best2]
  (if (> (max-best best1) (max-best best2)) best1 best2))

(defn max-interior-helper
  [bt]
  (cases bintree bt
    (leaf-node (num)
      (println 'max-interior "Empty tree"))
    (interior-node (key left right)
      (cases bintree left
        (leaf-node (num-l)
          (cases bintree right
            (leaf-node (num-r)
              (let [sum (+ num-l num-r)]
                (a-result sum (a-best sum key))
            (interior-node (key left right))
              (let* [result-r (max-interior-helper right)]
                      (sum (+ num-l (sum-result result-r)))
                (a-result sum (choose-maximum (a-best sum key)
                                              (best-result result-r)))))))))
        (interior-node (key left right)
          (cases bintree right
            (leaf-node (num-r)
              (let* [result-l (max-interior-helper left)]
                      (let [sum (+ (sum-result result-l) num-r)]
                (a-result sum (choose-maximum (a-best sum key)
                                              (best-result result-l)))))))
            (interior-node (key left right)
              (let* [result-l (max-interior-helper left)]
                      (let [result-r (max-interior-helper right)]
                      (let [sum (+ (sum-result result-l) (sum-result result-r))]
                (a-result sum
                          (choose-maximum
                            (a-best sum key)
                            (choose-maximum (best-result result-l)
                                            (best-result result-r))))))))))))

(defn max-interior
  [bt]
  (key-best (best-result (max-interior-helper bt))))
;;
;; unit-tests
;;
(def tree-1 (interior-node 'foo (leaf-node 2) (leaf-node 3)))
(def tree-2 (interior-node 'bar (leaf-node -1) tree-1))
(def tree-3 (interior-node 'baz tree-2 (leaf-node 1)))
