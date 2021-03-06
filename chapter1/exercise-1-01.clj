;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.1[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
;; Write inductive definitions of the following sets. Write each definition
;; in all three styles (top-down, bottom-up, and rules of inference). Using your
;; rules, show the derivation of some sample elements of each set.
;;
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.1
;;
;; {3n+2 | n in N} = {2, 5, 8, 11, ...}
;;
;; top-down: a natural number n is in S if and only if
;;   1) n=2, or
;;   2) n-3 in S.
;;
;; bottom-up: define the set S to be the smallest set contained in N and
;; satisfying the following two properties:
;;   1) 2 in S, and
;;   2) if n in S, then n+3 in S.
;;
;; rules of inference:
;;              n in S
;;   ------   ----------
;;   2 in S   (n+3) in S
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.2
;;
;; {2n+3m+1 | n,m in N} = {1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, ...}
;;
;; top-down: a natural number n is in S if and only if
;;   1) n=1, or
;;   2) n-2 in S, or
;;   3) n-3 in S.
;;
;; bottom-up: define the set S to be the smallest set contained in N and
;; satisfying the following three properties:
;;   1) 1 in S, and
;;   2) if n in S, then n+2 in S, and
;;   3) if n in S, then n+3 in S.
;;
;; rules of inference:
;;              n in S       n in S
;;   ------   ----------   ----------
;;   1 in S   (n+2) in S   (n+3) in S
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.3
;;
;; {(n,2n+1) | n in N} = {(0,1), (1,3), (2,5), (3,7), (4,9), ...}
;;
;; top-down: a pair of natural numbers (n,m) is in S if and only if
;;   1) (n,m)=(0,1), or
;;   2) (n-1,m-2) in S.
;;
;; bottom-up: define the set S to be the smallest set contained in N and
;; satisfying the following two properties:
;;   1) (0,1) in S, and
;;   2) if (n,m) in S, then (n+1,m+2) in S.
;;
;; rules of inference:
;;                 (n,m) in S
;;   ----------  --------------
;;   (0,1) in S  (n+1,m+2) in S
;;
;; ------------------------------------------------------------------------------
;; Exercise 1.1.4
;;
;; {(n,n^2) | n in N} = {(0,0), (1,1), (2,4), (3,9), (4,16), (5,25), ...}
;;
;; top-down: a pair of natural numbers (n,m) is in S if and only if
;;   1) (n,m)=(0,0), or
;;   2) (n-1,(m-2n+1) in S.
;;
;; bottom-up: define the set S to be the smallest set contained in N and
;; satisfying the following two properties:
;;   1) (0,0) in S, and
;;   2) if (n,m) in S, then (n+1,m+2n+1) in S.
;;
;; rules of inference:
;;                  (n,m) in S
;;   ----------  -----------------
;;   (0,0) in S  (n+1,m+2n+1) in S
