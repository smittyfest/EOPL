;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.7[**] @
;; @@@@@@@@@@@@@@@@@@@@
;;
;; Produce a more informative error message if
;; invalid input is given to the nth-elem function.
;;
(ns eopl.ch01 (:use clojure.test))

(defn nth-elem
  {:doc "Return nth-element of the list"}
  [lst n]
  (if (> n (count lst)) 
    (printf "Request was for element %d, but List has %d elements\n" n, (count lst)))
  (loop [cnt n tmp lst]
    (if (zero? cnt)
    (first tmp)
    (recur (dec cnt) (rest tmp)))))

;;
;; unit-tests
;;
(is (= (nth-elem '(a b c d e) 4) 'e))
(is (= (nth-elem '(a b c d e) 3) 'd))
(is (= (nth-elem '(a b c d e) 2) 'c))
(is (= (nth-elem '(a b c d e) 1) 'b))
(is (= (nth-elem '(a b c d e) 0) 'a))
(is (= (nth-elem '(a b c d e) 8) nil))
