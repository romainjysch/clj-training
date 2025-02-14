(ns librairies.network_ip
  (:require [clojure.network.ip :refer [make-network]])
  (:import com.googlecode.ipv6.IPv6Address
           com.googlecode.ipv6.IPv6Network))

;; very clean
(-> "2a04:c43:c00:5e74:500:1::/96"
    make-network
    second
    str)

(-> "2a04:c43:c00:5e74:500:1::/96"
    make-network)

(-> "2a04:c43:c00:5e74:500:1::/96"
    make-network
    first
    str)

;; less clean, but with tests
(let [network (IPv6Network/fromString "2a04:c43:c00:5e74:500:2::/96")
      addresses (.iterator network)
      first (.next addresses)
      second (.next addresses)]
  (str second))
