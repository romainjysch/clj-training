(ns librairies.json
  (:require [cheshire.core :as json]))

(json/generate-string {:firstname "Romain" :age 24}) ; generate json

(json/generate-string {:firstname "Romain" :age 24} {:pretty true}) ; with pretty formating

(json/generate-string {:romain {:age 24 :year-of-birth 1999 :size 174}})

(json/generate-smile {:firstname "Romain" :age 24}) ; generate smile