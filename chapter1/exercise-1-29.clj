;; ==============================================================================
;;
;; @@@@@@@@@@@@@@@@@@@@@
;; @ Exercise 1.29[**] @
;; @@@@@@@@@@@@@@@@@@@@@
;;
(declare merge)
(defn sort
  {:doc "sorts a list of integers"}
  [xs]
  (if (> (count xs) 1)
    (let [partitions (split-at (/ (count xs) 2) xs)]
      (merge (sort (get partitions 0)) (sort (get partitions 1))))
    xs))

(defn merge
  {:doc "given two lists of integers, returns a single sorted list"}
  [xs ys]
  (cond
    (empty? xs) ys
    (empty? ys) xs
    (<= (first xs) (first ys)) (cons (first xs) (merge (rest xs) ys))
    :else (cons (first ys) (merge xs (rest ys)))))
