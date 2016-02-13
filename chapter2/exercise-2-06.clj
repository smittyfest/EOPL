;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.6[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Map, Set, Tree, and Binary Search Tree representations of environments.
;;
(ns eopl.ch02 (:use clojure.test))

;;
;; Map representation
;;
(defn empty-env-map
  {:doc "Map Representation of an empty environment"}
  []
   {})

(defn extend-env-map
  {:doc "Create a new binding in an environment"}
  [var val env]
  (assoc env var val))

(defn apply-env-map
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (get env search-var))

;;
;; Set representation
;;
(defn empty-env-set
  {:doc "Set Representation of an empty environment"}
  []
    #{})

(defn extend-env-set
  {:doc "Create a new binding in an environment"}
  [var val env]
  (conj env (vector var val)))

(defn apply-env-set
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (if (sequential? (seq env))
    (let [[var val] (first env)]
      (if (= search-var var) val
      (recur search-var (rest env))))))

;;
;; Trivial Association-List "Tree"
;;
(defn empty-env-tree
  {:doc "Tree Representation of an empty environment"}
  []
    ())

(defn extend-env-tree
  {:doc "Create a new binding in an environment"}
  [var val env]
  (cons (vector var val) env))
  
(defn apply-env-tree
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (if (list? env)
    (let [[var val] (first env)]
      (if (= search-var var) val
      (recur search-var (rest env))))))

;;
;; Binary Search Tree representation
;;
;; 
;;
;; Binary Search Tree helpers
;;
(def gt? (comp pos? compare))
(def lt? (comp neg? compare))

;; 
;; Binary Search Tree implementation
;;
(definterface INode
  (leftChild [])
  (rightChild [])
  (insert [k v])
  (lookup [k])
  (inOrder []))

(deftype Node
  [key
   val
   ^:volatile-mutable ^INode left
   ^:volatile-mutable ^INode right]

  INode
  (leftChild [_] left)
  (rightChild [_] right)
  
  (insert
    [this k v]
    (let [n (Node. k v nil nil)]
      (cond
        (gt? k key) 
          (if right
            (.insert right k v)
            (set! right n))
        (lt? k key)
          (if left
            (.insert left k v)
            (set! left n)))))

  (lookup [this k]
    (if (= k key) val
      (cond
        (and (gt? k key) right) (.lookup right k)
        (and (lt? k key) left) (.lookup left k))))

  (inOrder
    [_]
    (lazy-cat
      (when left (.inOrder left))
      (vector key val)
      (when right (.inOrder right)))))
    
(defn bst
  {:doc "Creates a new Binary Search Tree"}
  [& [k v]]
  (Node. k v nil nil))

(defn- is-empty?
  {:doc "Tests if bst is empty"}
  [bst]
  (= bst ()))

(defn empty-env-bst
  {:doc "Binary Search Tree Representation of an empty environment"}
  []())

(defn extend-env-bst
  [var val env]
  (if (is-empty? env)
    (do
      (let [tree (Node. var val nil nil)]
      tree))  
    (do
      (.insert env var val)
      env)))

(defn apply-env-bst
  {:doc "Retrieve a value for a given variable in an environment"}
  [search-var env]
  (.lookup env search-var))

;;
;; unit-tests
;;
(is (= (empty-env-map) {}))
(is (= (empty-env-set) #{}))
(is (= (empty-env-tree) ()))
(is (= (extend-env-map :a 1 (empty-env-map)) {:a 1}))
(is (= (extend-env-map 'z \z (empty-env-map)) {'z \z}))
(is (= (extend-env-map :c 3 (extend-env-map :b 2 (extend-env-map :a 1 (empty-env-map)))) {:a 1, :b 2, :c 3}))
(is (= (extend-env-set :a 1 (empty-env-set)) #{[:a 1]}))
(is (= (extend-env-set :c 3 (extend-env-set :b 2 (extend-env-set :a 1 (empty-env-set)))) #{[:a 1] [:b 2] [:c 3]})) 
(is (= (extend-env-tree 'A \A (empty-env-tree)) '([A \A])))
(is (= (extend-env-tree :c 3 (extend-env-tree :b 2 (extend-env-tree :a 1 (empty-env-tree)))) '([:c 3] [:b 2] [:a 1])))
(is (= (apply-env-map :b (extend-env-map :c 3 (extend-env-map :b 2 (extend-env-map :a 1 (empty-env-map))))) 2))
(is (= (apply-env-map :b '{:a 1, :b 2, :c 3}) 2))
(is (= (apply-env-map :G (extend-env-map :H "Frost Giant" (extend-env-map :i "Tengu" (extend-env-map :G "Ghost-Tengu-FrostGiant" (empty-env-map))))) "Ghost-Tengu-FrostGiant"))
(is (= (apply-env-set :b (extend-env-set :c 3 (extend-env-set :b 2 (extend-env-set :a 1 (empty-env-set))))) 2))
(is (= (apply-env-set :b '#{[:a 1] [:b 2] [:c 3]}) 2))
;; internal representations are interchangeable
(is (= (apply-env-set :G (extend-env-map :H "Frost Giant" (extend-env-map :i "Tengu" (extend-env-map :G "Ghost-Tengu-FrostGiant" (empty-env-map))))) "Ghost-Tengu-FrostGiant"))
(is (= (apply-env-tree :b '([:c 3] [:b 2] [:a 1])) 2))
(def tree (bst :princess :toadstool))
(.insert tree :lukitu :cloud)
(.insert tree :hammer :brothers)
(is (= (.lookup tree :princess) :toadstool))
(is (= (.lookup tree :lukitu) :cloud))
(is (= (.inOrder tree) '(:hammer :brothers :lukitu :cloud :princess :toadstool)))
(is (= (empty-env-bst) ()))
(is (= (extend-env-bst :hammer :brothers (empty-env-bst))) '(:hammer :brothers))
(def env (extend-env-bst :princess :toadstool (extend-env-bst :lukitu :cloud (extend-env-bst :hammer :brothers (empty-env-bst)))))
(is (= (.inOrder env) '(:hammer :brothers :lukitu :cloud :princess :toadstool)))
(is (= (apply-env-bst :lukitu env) :cloud))
(is (= (apply-env-bst :hammer env) :brothers))
(is (= (apply-env-bst :princess env) :toadstool))
