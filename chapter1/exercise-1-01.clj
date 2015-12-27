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
;;               n in S
;;    ------   ----------
;;    2 in S   (n+3) in S
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.2
;;
;; {2n+3m+1 | n,m in N} = {1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, ...}
;;
;; top-down: a natural number n is in S if and only if
;; 1) n = 1, or
;; 2) n - 2 in S, or
;; 3) n - 3 in S.
;;
;; bottom-up: define the set S to be the smallest set contained in N and
;; satisfying the following three properties:
;; 1) 1 is in S, and
;; 2) n + 2 is in S if n in S, and
;; 3) n + 3 is in S if n in S.
;;
;; rules of inference:
;; 
;;               n in S       n in S
;;    ------   ----------   ----------
;;    1 in S   (n+2) in S   (n+3) in S
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.3
