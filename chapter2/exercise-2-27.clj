;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.27[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

;; one
((lambda (a) (a b)) c)

                 Lambda-Exp
                /          \
               /            \
        Lambda-Exp        Var-Exp
       /          \          |
  Bound-Var       Body      var
      |            |         |
      a         App-Exp      c
                /     \
             rator    rand
               |        |
            Var-Exp  Var-Exp
               |        |
              var      var
               |        |
               a        b

;; two
(lambda (x)
  (lambda (y)
    ((lambda (x)
       (x y))
     x)))

                  Lambda-Exp -----------------------
                 /                                  \
            Lambda-Exp                           Var-Exp
           /          \
      Bound-Var      Lambda-Exp
                  Bound-Var  Lambda-Exp
                            Bound-Var  App-Exp
