(defproject clj-training "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.github.seancorfield/next.jdbc "1.3.883"]
                 [com.oracle.ojdbc/ojdbc8 "19.3.0.0"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-jetty-adapter "1.9.4"]
                 [clj-http "3.12.3"]
                 [aleph "0.7.1"]
                 [cheshire "5.12.0"]]
  :repl-options {:init-ns clj-training.core})
