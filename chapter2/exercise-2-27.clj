;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.27[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Draw the abstract syntax tree for the lambda calculus expressions:
;;
;; one) ((lambda (a) (a b)) c)
;; two) (lambda (x) (lambda (y) ((lambda (x) (x y)) x)))
;;
(ns eopl.ch02 (:use clojure.test))

;; one
((lambda (a) (a b)) c)

                App-Exp
                /     \
             rator    rand
              |         |
        Lambda-Exp   Var-Exp
         /       \      |
    Bound-Var   Body   var
        |        |      |
        a     App-Exp   c
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

       Lambda-Exp
        /       \
  Bound-Var    Body
       |         |
       x     Lambda-Exp
              /      \
        Bound-Var    Body
             |        |
             y     App-Exp
                  /       \
               rator     rand
                 |         |
           Lambda-Exp   Var-Exp
            /      \       |
      Bound-Var   Body    var  
           |       |       |
           x    App-Exp    x
                /     \
            Var-Exp Var-Exp
               |       |
              var     var
               |       |
               x       y
