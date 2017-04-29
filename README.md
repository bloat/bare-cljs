This is the smallest project I could make to run a browser connected
[ClojureScript](https://clojurescript.org/) repl from
[Cider](https://cider.readthedocs.io/en/latest/), the Emacs
Clojure(Script) IDE. The project uses the [Boot](http://boot-clj.com/)
build tool.

It is mostly based on information gleaned from the second edition of
[Modern CLJS](https://github.com/magomimmo/modern-cljs) - the
excellent tutorial by Mimmo Cosenza.

Other information is from

* [Cider documentation](https://cider.readthedocs.io/en/latest/up_and_running/#browser-connected-clojurescript-repl-in-boot-project)
* The [README](https://github.com/adzerk-oss/boot-cljs-repl/blob/master/README.md) for the boot cljs-repl plugin.

This is project is set up for Cider version 0.14.0, make sure you've
installed that first. Here's some configuration for Cider:

#+BEGIN_SRC emacs-lisp
(with-eval-after-load "cider"
  (setq cider-jack-in-lein-plugins nil)
  (setq cider-jack-in-dependencies nil)
  (setq cider-boot-parameters "dev"))
#+END_SRC

We set the plugins and the dependencies to nil because the build.boot
file contains specifications for all the libraries needed. Cider can
inject them automatically, but I couldn't get things working that
way. Note that even though the variable is called lein-plugins, it is
also used for Boot projects. Lastly we make sure Cider uses the task
named "dev", which is also defined in [build.boot].

Then, to get a running browser connected repl, open one of the files
in the project, for example core.cljs. Run M-x
cider-jack-in-clojurescript, and wait a little while. Two repl windows
will appear, and Emacs will ask if you want to open your browser. On
my computer at least, I need to wait at least 20 seconds or so for
everything to finish starting up and compiling, before answering yes
to this question. The browser should start, and you should see
"connected!" in one of the repls. This is now your browser connected
ClojureScript repl (the other repl is for the underlying Clojure
process.) In the ClojureScript repl you can now type something like
=(js/alert "Hello, World!")= and see the result in your browser.

# License

Copyright © Andrew Cowper, 2017. Released under the Eclipse Public
License, the same license as Clojure.