(ns librairies.reitit)

(comment
  (require '[reitit.core :as r])
  (def router
    (r/router
      [["/api/ping" ::ping]
       ["/api/orders/:id" ::order]]))
  (r/match-by-path router "/api/ping")
  (r/match-by-name router ::order {:id 2}))