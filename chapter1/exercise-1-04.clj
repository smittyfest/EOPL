;; ------------------------------------------------------------------------------
;; Exercise 1.4[*]
;;
;; A Syntactic Derivation from List[Int] to
;; (-7 . (3 . (14 . ()))):
;;
;;      List[Int]
;;   => (Int . List[Int])
;;   => (14  . List[Int])
;;   => (14  . ())
;;   => (3   . (14 . ()))
;;   => (-7  . (3 . (14 . ())))
