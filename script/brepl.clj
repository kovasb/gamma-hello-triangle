(require '[cljs.repl :as r])
(require '[cljs.build.api :as b])
(require '[cljs.repl.browser :as brepl])

(b/build "src"
  {:main 'gamma-hello-triangle.dev
   :asset-path "js"
   :output-to "resources/public/js/main.js"
   :output-dir "resources/public/js"})

(r/repl
  (brepl/repl-env
    :static-dir ["resources/public" "resources/public/js"])
  :output-dir "resources/public/js")