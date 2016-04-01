;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.27[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

;; one
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
                  Lambda-Exp -----------------------
                 /                                  \
            Lambda-Exp                           Var-Exp
           /          \
      Bound-Var      Lambda-Exp
                  Bound-Var  Lambda-Exp
                            Bound-Var  App-Exp
