;; ------------------------------------------------------------------------------
;; Exercise 1.12[*]
;;
;; S-list ::= ()
;;        ::= (S-exp . S-list)
;;  S-exp ::= Symbol | S-list
;;
(ns eopl.ch01 (:use clojure.test))
(defn subst
  {:doc "Return a new S-List with each occurrence of 'old' replaced by 'new'"}
  [old new lst]
  (if (empty? lst)
      ()
      (cons
        (if (symbol? (first lst)) ;; inlined subst-in-s-exp procedure
          (if (= (first lst) old) new (first lst))
          (subst old new (first lst)))
        (subst old new (rest lst)))))
;;
;; run some unit-tests
;;
(is (= (subst 'b 'a ()) ()))
(is (= (subst 'b 'a '(b b a a)) '(a a a a)))
(is (= (subst 'b 'a '(a a a a)) '(a a a a)))
(is (= (subst 'b 'a '(b a '(b c) z b a)) '(a a '(a c) z a a)))
