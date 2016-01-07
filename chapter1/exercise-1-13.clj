
;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.13[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
;; In our example, we began by eliminating the Kleene Star in the
;; grammar for S-List. Write subst following the original grammar by using map.
;;
;; ~ Original Grammar ~
;; S-List ::= ({S-Exp}*)
;;  S-Exp ::= Symbol | S-List
;;
(ns eopl.ch01 (:use clojure.test))
(defn subst
  {:doc "Return a new S-List with each occurrence of 'old' replaced by 'new'"}
  [old new lst]
  (if (empty? lst)
      ()
      (map (fn [exp]
        (if (symbol? exp)
          (if (= old exp) new exp)
        (subst old new exp)))
      lst)))

(is (= (subst 'b 'a ()) ()))
(is (= (subst 'b 'a '(b b a a)) '(a a a a)))
(is (= (subst 'b 'a '(a a a a)) '(a a a a)))
(is (= (subst 'b 'a '(b a '(b c) z b a)) '(a a '(a c) z a a)))
