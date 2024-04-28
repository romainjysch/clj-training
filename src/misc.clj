(ns misc)

(def multiply-by-3 #(* 3 %))
(multiply-by-3 3)
(multiply-by-3 3)

(def multiply #(* %1 %2))
(multiply 2 3)

(def my-double (fn [x] (* 2 x)))
(my-double 2)