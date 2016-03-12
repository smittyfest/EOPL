;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.20[***] @
;; @@@@@@@@@@@@@@@@@@@@@@
;;
;; In the representation of binary trees in exercise-2.19, it is easy to
;; move from a parent node to one of its sons, but it is impossible to move from a son to
;; its parent without the help of context arguments. Extend the representation of lists in
;; exercise-2.18 to represent nodes in a binary tree. As a hint, consider representing the
;; portion of the tree above the current node by a reversed list, as in exercise-2.18.
;;
;; In this representation, implement the procedures from exercise-2.19. Also implement
;; move-up, at-root?, and at-leaf?.
;;
(ns eopl.ch02 (:use clojure.test))

(defn leaf [] ())
(defn at-leaf? [t] (nil? t))

(defn fork [n l r] (list n l r))
(defn current-element [node] (first (seq node)))
(defn move-to-left [exp] (first (rest (seq exp))))
(defn move-to-right [exp] (first (rest (rest (seq exp)))))

(defn number->bintree [n] (fork n (leaf) (leaf)))

(defn insert-to-left
  {:doc ""}
  [n t]
  (fork (current-element t)
    (fork n (move-to-left t) (leaf))
    (move-to-right t)))

(defn insert-to-right
  {:doc ""}
  [n t]
  (fork (current-element t)
    (move-to-left t)
    (fork n (leaf) (move-to-right t))))

;; represent a location in the binary tree as a pair of a tree (the current sub-tree)
;; and a context (the steps to reconstruct the initial tree).
(def location cons)
(defn location->tree [tree] (first tree))
(defn location->context [tree] (rest tree))

;; A context is a stack of context elements.
(defn root-context [] ())
(defn root-context? [t] (nil? t))
(def push-context-element cons)
(defn context->top [tree] (first tree))
(defn context->parent [tree] (rest tree))

; A context element consists of a direction, a value and a tree.
(defn context-element [d n t] (list d n t))
(defn context-element->tag [node] (first (seq node)))
(defn context-element->value [node] (first (rest (seq node))))
(defn context-element->tree [node] (first (rest (rest (seq node)))))
(defn left [n t] (context-element 'left n t))
(defn right [n t] (context-element 'right n t))
(defn left? [e] (= (context-element->tag e) 'left))

(defn number->bintree-loc
  {:doc ""}
  [n]
  (location (number->bintree n) (root-context)))

(defn current-element-loc
  {:doc ""}
  [l]
  (current-element (location->tree l)))

(defn move-up-loc
  {:doc ""}
  [l]
  (let ((context (location->context l)))
    (if (root-context? context)
    (println 'move-up "already at root: ~s" l)
      (let ((top (context->top context)))
        (location (if (left? top)
          (fork (context-element->value top)
          (location->tree l)
          (context-element->tree top))
          (fork (context-element->value top)
          (context-element->tree top)
          (location->tree l)))
          (context->parent context))))))

(defn move-to-left-loc
  {:doc ""}
  [l]
  (let ((t (location->tree l)))
    (location (move-to-left t)
    (push-context-element (left (current-element t)
    (move-to-right t))
    (location->context l)))))

(defn move-to-right-loc
  {:doc ""}
  [l]
  (let ((t (location->tree l)))
    (location (move-to-right t)
    (push-context-element (right (current-element t)
    (move-to-left t))
    (location->context l)))))

(defn at-root?-loc
  [l]
  (root-context? (location->context l)))

(defn at-leaf?-loc
  {:doc ""}
  [l]
  (at-leaf? (location->tree l)))

(defn insert-to-left-loc
  {:doc ""}
  [n l]
  (location (insert-to-left n (location->tree l))
    (location->context l)))

(defn insert-to-right-loc
  {:doc ""}
  [n l]
  (location (insert-to-right n (location->tree l))
    (location->context l)))
;;
;; unit-tests
;;
