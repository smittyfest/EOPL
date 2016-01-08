;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.18[*] @
;; @@@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch01 (:use clojure.test))
(defn swapper
  {:doc "Return a new S-List with each occurrences of a and b swapped"}
  [a b lst]
  (if (empty? lst)
    ()
    (map (fn [exp]
      (if (symbol? exp)
        (cond
          (= a exp) b
          (= b exp) a
          :else exp)
      (swapper a b exp)))
     lst)))
;;
;; unit-tests
;;
(is (= (swapper 'b 'a ()) ()))
(is (= (swapper 'b 'a '(b b a a)) '(a a b b)))
(is (= (swapper 'b 'a '(a a a a)) '(b b b b)))
(is (= (swapper 'a 'd '(a b c d)) '(d b c a)))
(is (= (swapper 'a 'd '(a d () c d)) '(d a () c a)))
(is (= (swapper 'x 'y '((x) y (z (x)))) '((y) x (z (y)))))
(is (= (swapper 'b 'a '(b a (b c) z b a)) '(a b (a c) z a b)))
