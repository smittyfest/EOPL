;; ------------------------------------------------------------------------------
;; Exercise 1.1[*]
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.1
;;
;; {3n+2 | n in N} = {2, 5, 8, 11, ...}
;;
;; top-down: a natural number n is in S if and only if
;; 1) n = 2, or
;; 2) n - 3 in S.
;;
;; bottom-up: define the set S to be the smallest set contained in N and
;; satisfying the following two properties:
;; 1) 2 is in S, and
;; 2) n + 3 is in S if n in S.
;;
;; rules of inference:
;; ------
;; 2 in S
;;
;; n in S
;; ----------
;; n + 3 in S
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.2
