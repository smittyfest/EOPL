;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.4[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Consider the data type of Stacks of values, with an interface consisting
;; of the procedures 
;; (empty)       = ()
;; (push [f] s)  = (f . s)
;; (pop s)       = (cdr s)
;; (top s)       = (car s)
;; (empty? s)    = {#t if s = (), #f otherwise}
;;
;; constructors: empty, push, pop
;; observers: top, empty?
