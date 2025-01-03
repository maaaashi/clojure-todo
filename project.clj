(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ch.qos.logback/logback-classic "1.4.11"]
                 [nubank/mockfn "0.7.0"]
                 [ring/ring-core "1.13.0"]
                 [ring/ring-json "0.5.1"]
                 [ring/ring-jetty-adapter "1.13.0"]
                 [com.github.seancorfield/next.jdbc "1.3.967"]
                 [org.postgresql/postgresql "42.7.4"]
                 [compojure "1.7.1"]
                 [org.clojure/data.json "2.5.1"]
                 [org.clojure/tools.logging "1.3.0"]
                 [ring-logger "1.1.1"]]
  :main todo.core
  :repl-options {:init-ns todo.core})
