
;; ------------------------------------------------------------------------------
;; Exercise 1.11[*]
;;
;; S-list ::= ()
;;        ::= (S-exp . S-list)
;;  S-exp ::= Symbol | S-list
;;
(ns eopl.ch01 (:use clojure.test))

(declare subst-in-s-exp)

(defn subst
  {:doc "Return a new S-List with each occurrence of 'old' replaced by 'new'"}
  [old new lst]
  (if (empty? lst)
      ()
      (cons
        (subst-in-s-exp old new (first lst))
        (subst old new (rest lst)))))

(defn subst-in-s-exp
  {:doc "Return a new S-Exp with each occurrence of 'old' replaced by 'new'"}
  [old new exp]
  (if (symbol? exp)
    (if (= exp old) new exp)
    (subst old new exp)))
;;
;; run some unit-tests
;;
(is (= (subst 'b 'a ()) ()))
(is (= (subst 'b 'a '(b b a a)) '(a a a a)))
(is (= (subst 'b 'a '(a a a a)) '(a a a a)))
(is (= (subst 'b 'a '(b a '(b c) z b a)) '(a a '(a c) z a a)))
