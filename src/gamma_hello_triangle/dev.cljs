(ns gamma-hello-triangle.dev
  (:require [clojure.browser.repl :as repl]
            gamma-hello-triangle.core))

(defonce conn
  (repl/connect "http://localhost:9000/repl"))

(gamma-hello-triangle.core/main)