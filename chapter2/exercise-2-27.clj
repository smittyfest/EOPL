;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.27[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

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
               
