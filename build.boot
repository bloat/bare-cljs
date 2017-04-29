(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"html"}

 :dependencies '[[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.473"]
                 [adzerk/boot-cljs "1.7.228-2"]         ;; Let boot run the ClojureScript compiler
                 [pandeiro/boot-http "0.7.6"]           ;; Let boot run a web server
                 [org.clojure/tools.nrepl "0.2.12"]     ;; Provide the nRepl server
                 [adzerk/boot-cljs-repl "0.3.3"]        ;; Let boot start a ClojureScript repl
                 [com.cemerick/piggieback "0.2.1"]    
                 [weasel "0.7.0"]                       ;; Piggieback and Weasel are infrastructure to run a ClojureScript repl
                 [cider/cider-nrepl "0.14.0"]])         ;; Let Cider connect to our nRepl server

;; Provide the individual tasks which make up the "dev" task.
(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])

(swap! boot.repl/*default-middleware*
       conj 'cider.nrepl/cider-middleware)   ;; Make sure the Cider nRepl code is loaded

(deftask dev
  "Start ClojureScript Browser Repl"
  []
  (comp
   (serve :dir "target")         ;; Start a web server
   (watch)                       ;; Recompile changed files
   (cljs-repl)                   ;; Run a ClojureScript repl
   (cljs)                        ;; Compile ClojureScript files
   (target :dir #{"target"})))   ;; Define location of compiled files
