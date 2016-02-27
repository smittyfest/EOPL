;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@
;; @ Exercise 2.1[*] @
;; @@@@@@@@@@@@@@@@@@@
;;
(ns eopl.ch02 (:use clojure.test))

(def BASE 10)

(def zero ()) ;; returns bigint representation of zero

(defn is-zero?
  {:doc "returns true if n is equal to the bigint representation of zero"}
  [n]
  (empty? n))

(defn successor
  {:doc "returns the successor to n in bigint representation"}
  [n]
  (cond
    (is-zero? n) '(1)
    (= (inc (first n)) BASE) (cons 0 (successor (rest n)))
    :else (cons (+ (first n) 1) (rest n))))

(defn predecessor
  {:doc "returns the predecessor to n in bigint representation"}
  [n]
  (cond
    (zero? (first n)) (cons (- BASE 1) (predecessor (rest n)))
    (and (= (first n) 1) (is-zero? (rest n))) ()
    :else (cons (- (first n) 1) (rest n))))

;; more functions
(defn plus
  {:doc "adds two integers"}
  [x y]
  (if (is-zero? x) y
    (successor (plus (predecessor x) y))))

(defn multiply
  {:doc "multiplies two integers"}
  [x y]
  (cond
    (or (is-zero? x) (is-zero? y)) zero
    (is-zero? (predecessor x)) y
    :else (plus y (multiply (predecessor x) y))))  
 
;; factorial
(defn fact
  {:doc "returns the factorial of n"}
  [n]
  (if (or (is-zero? n) (is-zero? (predecessor n))) (successor zero)
    (multiply n (fact (predecessor n)))))
;;
;; example constants
;;
(def one (successor ()))
(def two (successor (successor ())))
(def three (successor (successor (successor ()))))
(def four (successor (successor (successor (successor ())))))
(def five (successor (successor (successor (successor (successor ()))))))
(def six (successor (successor (successor (successor (successor (successor ())))))))
(def seven (successor (successor (successor (successor (successor (successor (successor ()))))))))
(def eight (successor (successor (successor (successor (successor (successor (successor (successor ())))))))))
(def nine (successor (successor (successor (successor (successor (successor (successor (successor (successor ()))))))))))
(def ten (successor (successor (successor (successor (successor (successor (successor (successor (successor (successor ())))))))))))
;;
;; unit-tests
;;
(is (= ('zero ())))
(is (= (is-zero? ()) true))
(is (= ('one '(1))))
(is (= ('two '(2))))
(is (= ('three '(3))))
(is (= ('four '(4))))
(is (= ('five '(5))))
(is (= ('six '(6))))
(is (= ('seven '(7))))
(is (= ('eight '(8))))
(is (= ('nine '(9))))
(is (= ('ten '(10))))
(is (= (predecessor '(11)) '(10)))
(is (= (predecessor '(10)) '(9)))
(is (= (predecessor '(9)) '(8)))
(is (= (predecessor '(8)) '(7)))
(is (= (predecessor '(7)) '(6)))
(is (= (predecessor '(6)) '(5)))
(is (= (predecessor '(5)) '(4)))
(is (= (predecessor '(4)) '(3)))
(is (= (predecessor '(3)) '(2)))
(is (= (predecessor '(2)) '(1)))
(is (= (predecessor '(1)) ()))
(is (= (plus '(2) '(2)) '(4)))
(is (= (plus '(5) '(3)) '(8)))
(is (= (multiply '(4) '(2)) '(8)))
;; factorial times
(println (time (fact ())))
;; => "Elapsed time: 0.045669 msecs" (1)
(println( time (fact '(1))))
;; => "Elapsed time: 0.036646 msecs" (1)
(println(time (fact '(2))))
;; => "Elapsed time: 0.07091 msecs" (2)
(println (time (fact '(3))))
;; => "Elapsed time: 0.132475 msecs" (6)
(println (time (fact '(4))))
;; => "Elapsed time: 0.3064 msecs" (4 2)
(println (time (fact '(5))))
;; => "Elapsed time: 1.086547 msecs" (0 2 1)
(println (time (fact '(6))))
;; => "Elapsed time: 3.584692 msecs" (0 2 7)
(println (time (fact '(7))))
;; => "Elapsed time: 8.953824 msecs" (0 4 0 5)
(println (time (fact '(8))))
;; => "Elapsed time: 20.333307 msecs" (0 2 3 0 4)
(println (time (fact '(9))))
;; => Exception in thread "main" java.lang.StackOverflowError

;; This implementation is unsatisfactory since it leads to a stack-overflow.
;; Next implementation uses tail-recursion to preserve the stack.

(defn f
  [n]
  (loop [x n z 1]
    (if (= x (successor zero)) '(1)
      (recur (predecessor x) (multiply z x)))))
    
(defn factorials []
    (letfn [(factorial-seq [n fact]
                           (lazy-seq
                             (cons fact (factorial-seq (inc n) (* (inc n) fact)))))]
      (factorial-seq 1 1)))

(take 5 (factorials)) ; will return (1 2 6 24 120)

;; other factorial implementations
(def facts (lazy-cat [1] (map * facts (iterate inc 2)))) ;; Then (take 5 facts) produces (1 2 6 24 120)
(def facts2 (reductions * (iterate inc 1)))
(defn factorial2 [n] (reduce * (range 1 (inc n))))

;; As the argument increases, the execution time dramatically increases,
;; because we need to compute predecessors and successors on larger and larger
;; numbers. Besides the bad performance characteristices, non-tail-recursive
;; implementations will result in a stack-overflow for even small integers.
;; The performance does not have as much variation as the base changes. Even large
;; changes in base result in small differences in overall performance.

;; This implementation is pretty terrible.
;; We can easily swap in better data representations and implementations
;; while keeping client code independent of these internal details.

;; Tail-Recursion
;;
;; Improved factorial function that utilizes tail-recursion to recycle the stack frame
;;
(defn factorial-tail-recursive
  [n]
    (loop [x n accu 1]
      (if (<= x 1) accu
        (recur (dec x) (* accu x)))))

;; Test the tail-recursive factorial function
;;
;; (println (time (factorial-tail-recursive 0)))
;; => "Elapsed time: 0.026 msecs" 1
;; (println (time (factorial-tail-recursive 1)))
;; => "Elapsed time: 0.024 msecs" 1
;; (println (time (factorial-tail-recursive 2)))
;; => "Elapsed time: 0.03 msecs" 2
;; (println (time (factorial-tail-recursive 3)))
;; => "Elapsed time: 0.037 msecs" 6
;; (println (time (factorial-tail-recursive 4)))
;; => "Elapsed time: 0.042 msecs" 24
;; (println (time (factorial-tail-recursive 5)))
;; => "Elapsed time: 0.04 msecs" 120
;; (println (time (factorial-tail-recursive 6)))
;; => "Elapsed time: 0.048 msecs" 720
;; (println (time (factorial-tail-recursive 7)))
;; => "Elapsed time: 0.048 msecs" 5040
;; (println (time (factorial-tail-recursive 8)))
;; => "Elapsed time: 0.052 msecs" 40320
;; (println (time (factorial-tail-recursive 9)))
;; => "Elapsed time: 0.056 msecs" 362880
;; (println (time (factorial-tail-recursive 10)))
;; => "Elapsed time: 0.056 msecs" 3628800
;; (println (time (factorial-tail-recursive 11)))
;; => "Elapsed time: 0.06 msecs" 39916800
;; (println (time (factorial-tail-recursive 12)))
;; => "Elapsed time: 0.061 msecs" 479001600

;; Let's try a really big number
;; (println (time (factorial-tail-recursive 20)))
;; => "Elapsed time: 0.088 msecs" 2432902008176640000


;; Lazy Infinite Sequences
;;
;; Even better(?) factorial function that utilizes chunked lazy-sequences
;;
(defn factorial-lazy-seq
  [n]
    (reduce * (range 1 (inc n))))

;;
;; lazy-seq factorial function
;;
(defn factorial2
  [n]
    (reduce * (range 1 (inc n))))

;;
;; Test the lazy-seq factorial function
;;
;; (println (time (factorial-lazy-seq 0)))
;; => "Elapsed time: 0.07 msecs"  1
;; (println (time (factorial-lazy-seq 1)))
;; => "Elapsed time: 0.049 msecs" 1
;; (println (time (factorial-lazy-seq 2)))
;; => "Elapsed time: 0.057 msecs" 2
;; (println (time (factorial-lazy-seq 3)))
;; => "Elapsed time: 0.067 msecs" 6
;; (println (time (factorial-lazy-seq 4)))
;; => "Elapsed time: 0.061 msecs" 24
;; (println (time (factorial-lazy-seq 5)))
;; => "Elapsed time: 0.062 msecs" 120
;; (println (time (factorial-lazy-seq 6)))
;; => "Elapsed time: 0.065 msecs" 720
;; (println (time (factorial-lazy-seq 7)))
;; => "Elapsed time: 0.065 msecs" 5040
;; (println (time (factorial-lazy-seq 8)))
;; => "Elapsed time: 0.071 msecs" 40320
;; (println (time (factorial-lazy-seq 9)))
;; => "Elapsed time: 0.072 msecs" 362880
;; (println (time (factorial-lazy-seq 10)))
;; => "Elapsed time: 0.071 msecs" 3628800
;; (println (time (factorial-lazy-seq 11)))
;; => "Elapsed time: 0.073 msecs" 39916800
;; (println (time (factorial-lazy-seq 12)))
;; => "Elapsed time: 0.072 msecs" 479001600

;; Let's try a really big number
;; (println (time (factorial-lazy-seq 20)))
;; => "Elapsed time: 0.088 msecs" 2432902008176640000

;;
;; Aww Yiss...
;;
;; This won't blow the stack because range returns a lazy seq,
;; and reduce walks across the seq without holding onto the head.
;; reduce makes use of chunked seqs if it can,
;; so this can actually perform better than using recur.
;;
;; This shows the power of using interfaces to separate client code
;; from specific data representations or implementations.
;;
;; The client code never had to change even though we have experimented
;; with several implementations;
