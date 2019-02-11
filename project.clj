(defproject netjson "0.1.0-SNAPSHOT"
  :description "Pizza eaten, lets hack"
  :url "https://example.com"
  :license {:name "GNU GPLv3+"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cheshire "5.8.1"]
                 [org.clojure/clojurescript "1.10.516"]
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]]
  :profiles {:dev {:plugins [[lein-cljsbuild "1.1.7"]]
                   :cljsbuild {:builds [{:source-paths ["src"]
                                         :compiler {:output-to "target/classes/public/app.js"
                                                    :optimizations :whitespace
                                                    :pretty-print true}}]}}}
  :resource-paths ["resources" "resources/netjson/"]
  :main netjson.core)
