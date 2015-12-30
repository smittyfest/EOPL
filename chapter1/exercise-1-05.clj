;; ------------------------------------------------------------------------------
;; Exercise 1.5[**]
;;
;; Prove that if expression e is in LcExp, then there are the same number
;; of left and right parentheses in e.
;;
;; Proof: The proof is by structural induction on the size k of e,
;; where k is the number of symbolic expressions in e.
;; The induction hypothesis IH(k) is that any symbolic expression
;; e having size <= k has a matching number of left and right parentheses.
;;
;; 1. LcExp ::= Identifier, where Identifier has no parentheses, so IH(0) holds trivially.
;; 2. Let k be an integer such that IH(k) holds, that is, any symbolic expression
;;    with <= k sub-expressions actually has matching left and right parentheses.
;;    We need to show that IH(k+1) holds as well: that any s-expression with <= k+1
;;    sub-expressions has a matching number of left and right parentheses. If e has
;;    <= k+1 sub-expressions there are exactly three possibilities according to the
;;    definition of LcExp:
;;
;;    a) e could be of the form 'Identifier', where identifiers have no parentheses,
;;       and thus has a matching number (0) of left and right parentheses.
;;    b) e could be of the form (λ (Identifier) LcExp), where λ is a symbol containing
;;       no parentheses, Identifier is an argument with matching parentheses and LcExp
;;       is another lambda expression. The entire form is surrounded by a matching left
;;       and right parentheses, as is Identifier. Therefore this case holds.
;;    c) e could be of the form (LcExp LcExp). This form is surrounded by matching parentheses
;;       and each LcExp must have fewer sub-expressions than e. Since e has <= k+1
;;       sub-expressions, both LcExp instances must have <= k sub-expressions. Therefore
;;       they are covered by IH(k) and they must have a matching number of left and right
;;       parentheses.
