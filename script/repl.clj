(require '[cljs.repl :as r])
(require '[cljs.repl.node :as n])

(r/repl (n/repl-env))