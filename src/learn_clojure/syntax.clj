(ns learn-clojure.syntax)

42
-1.5
5/2

"hello"
\e

clojure.core/+

'(1 2 3)
[1 2 3]
(type #{1 2 3})
{:a 2 :b 2}

'x

(+ 3 4)
(+ 10 *1)
(+ *1 *2)

; *1 the last result
; *2 the result two expressions ago
; *3 the result three expressions ago

; for doc, find-doc, apropos, source and dir:
(require '[clojure.repl :refer :all])

(doc +)
(doc doc)
(apropos "+")
(doc apropos)
(find-doc "trim")

(dir clojure.repl)
(source dir)
(doc dir)

(println "What is this:" (+ 1 2))
(prn "one\n\ttwo")

;; Test your knowledge:
(+ 7654 1234)
(/ (+ 7 (* 3 4) 5) 10)
(doc rem)
(rem 12 5)
(doc mod)
(mod 12 5)
(find-doc "trace")