;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.17[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn down
  {:doc "wraps parentheses around each top-level element of a list"}
  [xs]
  (partition 1 xs))

(defn down2
  {:doc "wraps parentheses around each top-level element of a list in a lazy sequence"}
  [xs]
  (lazy-seq
    (when-let [s (seq xs)]
      (let [p (doall (take 1 s))]
        (when (= 1 (count p))
          (cons p (down2 (nthrest s 1))))))))
;;
;; unit-tests
;;
(is (= (down ()) ()))
(is (= (down '(1 2 3)) '((1) (2) (3))))
(is (= (down '((a) (fine) (idea))) '(((a)) ((fine)) ((idea)))))
(is (= (down '(a (more (complicated)) object)) '((a) ((more (complicated))) (object))))
(is (= (down2 ()) ()))
(is (= (down2 '(1 2 3)) '((1) (2) (3))))
(is (= (down2 '((a) (fine) (idea))) '(((a)) ((fine)) ((idea)))))
(is (= (down2 '(a (more (complicated)) object)) '((a) ((more (complicated))) (object))))
