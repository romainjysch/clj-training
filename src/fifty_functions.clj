(ns fifty_functions
  (:require [clojure.string :as str]))

;; based on: https://www.youtube.com/watch?v=4mDX4pqAVm0

;; vectors
[1 2 3 :a "toto" [1 2 3]]

;; sets
#{1 2 3}
;#{1 2 3 2} ; exception

;; maps
{"key" "value"
 "a" "b"
 :a 1
 [1 2 3] :a}
(let [a-map {[1 2] "numbers"}]
  (get a-map [1 2]))

;; lists
'(1 2 3)
(type '(1 2))
(list 1 2)

;; sequences
; every coll is a seq
(seq [1 2 3])
(seq nil) ; returns nil
(seq []) ; also returns nil
(when (seq [1 2 3])
  :not-empty)
(when (empty? [1 2 3])
  :empty) ; nil
(when (empty? [])
  :empty) ; :empty
(when (not (empty? [1 2 3]))
  :not-empty) ; worse than (when (seq []))

;; get
(get [1 2 3] 0)
(get [1 2 3] 3) ; nil
(get [1 2 3] 3 :default) ; :default
(get {:a 1} :a)

;; get in
(get-in {:a {:b {:c 1}}}
        [:a :b :c])
(get-in {:a {:b {:c 1}}}
        [:a :b :d]
        :default) ; :default

;; assoc
(assoc {:a 1} :b 2)
(assoc {:a 1} :b 2 :c 3)
(-> {:a 1}
    (assoc :b 2)
    (assoc :c 3))

;; assoc in
(let [orignal-map {:a {:b {:c 1}}}]
  (assoc-in orignal-map [:a :b :d] 2))

;; dissoc
(dissoc {:a 1 :b 2} :b)
(dissoc {:a 1 :b 2} :a :b)

;; update
(update {:a 1 :b 2} :a inc)
(update {:a 1 :b 2} :a + -5)
(update {:a 1 :b 2} :a #(inc %))

;; update-in
(update-in {:a {:b 2}} [:a :b] inc)

;; merge
(merge {:a 1 :map {:x 1}} {:map {:y 2} :b 2 :c 3} {:a 10 :d 4})

;; merge-with
(merge-with merge {:map {:x 1}} {:map {:y 2} :b 2 :c 3} {:a 10 :d 4})

;; count
(count [1 2])
(count [1 2 [3 4]])
(count {:a 1 :b 2})

;; sum
(+ 1 2 3)

;; apply
(apply + [1 2 3])

;; first
(first [1 2])
(first {:a 1 :b 2})

;; second
(second {:a 1 :b 2})

;; nth
(nth [1 2 3] 0)

;; rest
(rest [1 2 3])

;; last
(last [1 2 3])

;; butlast
(butlast [1 2 3 4])

;; take
(take 2 [1 2 3 4])
(take 3 [1 2 3 4])
(take 10 [1 2 3 4])

;; drop
(drop 2 [1 2 3 4 5])

;; drop-last
(drop-last 3 [1 2 3 4 5])

;; flatten
(flatten [1 2 3 [4 5 [6 7]]])
(vec (flatten [1 2 3 [4 5 [6 7]]]))

;; map
(map inc [1 2 3])
(map #(* 3 %) [1 2 3])
(let [square #(* % %)]
  (map square [1 2 3]))

;; map with multiple collections
(map + [1 2 3] [1 2 3 4]) ; in order

;; map-indexed
(map-indexed (fn [index val] [index val]) [1 2 3])

;; mapv
(mapv inc [1 2 3])

;; mapcat
(flatten (map (fn [x] [x x x]) [1 2 3]))
(mapcat (fn [x] [x x x]) [1 2 3])

;; filter
(filter pos? [1 -1 0 3 -5])
(filter zero? [1 -1 0 3 -5])

;; filterv
(filterv pos? [1 -1 0 3 -5])

;; remove
(remove pos? [1 -1 0 3 -5])

;; keep
(->> [1 2 3]
     (map (fn [x]
            (if (= x 2)
              nil
              x)))
     (remove nil?))
(->> [1 2 3]
     (keep (fn [x]
             (if (= x 2)
               nil
               x))))

;; keep-indexed
(->> [1 2 3]
     (keep-indexed (fn [index x]
                     (if (= x 2)
                       nil
                       [index x]))))

;; into
(into #{} [1 2 3 4 1 2])
(into {} [[:a 1] [:b 2]])

;; group-by
(group-by pos? [1 2 3 -1 0 -3]) ; {true [1 2 3], false [-1 0 -3]}
(group-by :type [{:type 1 :value 1} {:type 2 :value 2} {:type 1 :value "one"}])

;; frequencies
(frequencies [1 2 3 4 4 1 2 2])

;; partition
(partition 2 [1 2 3 4])
(partition 3 [1 2 3 4 5]) ; ((1 2 3))
(partition 2 1 [1 2 3 4 5]) ; ((1 2 3))

;; partition-all
(partition-all 2 [1 2 3 4 5]) ; ((1 2) (3 4) (5))

;; shuffle
(shuffle [1 2 3 4 5])

;; reduce
(reduce (fn [acc x] (+ acc x)) [1 2 3])
(reduce + [1 2 3])
(reduce + [1 2 3])
(reduce + -6 [1 2 3])

;; update-keys
(update-keys {"a" 1 "b" 2} keyword)
(-> {"a" 1 "b" 2}
    (update-keys str/upper-case)
    (update-keys keyword))

;; update-vals
(update-vals {:a 1 :b 1 :c 1} inc)

;; sort
(sort [1 2 10 99 -1 0 5])
(reverse (sort [1 2 10 99 -1 0 5]))

;; sort-by
(sort-by :id [{:id 1} {:id 23} {:id 5}])

;; interleave
(interleave [1 2 3] [:a :b :c] ["1" "2" "3"]) ; (1 :a "1" 2 :b "2" 3 :c "3")

;; interpose
(interpose " / " [1 2 3])

;; repeat
(repeat 10 1)

;; repeatedly
(repeatedly 10 (fn [] "text"))

;; range
(take 10 (range))
(range 0 50)
(range 0 10)

;; cycle
(take 10 (cycle [1 2])) ; (1 2 1 2 1 2 1 2 1 2)

;; distinct
(distinct [1 1 2 2 3 3 4]) ; (1 2 3 4)

;; select-keys
(select-keys {:a 1 :b 2 :c 3} [:a :c]) ; {:a 1, :c 3}

;; juxt
((juxt :a :b) {:a [1 2 3] :b [3 4 5]})
(flatten ((juxt :a :b) {:a [1 2 3] :b [3 4 5]}))
