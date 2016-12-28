(set-env! :resource-paths #{"src"}
          :dependencies   '[[org.clojure/clojure "1.8.0" :scope "provided"]
                            [me.raynes/fs "1.4.6"]
                            [adzerk/bootlaces "0.1.13" :scope "testing"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0-SNAPSHOT")

(bootlaces! +version+)

(task-options!
  pom {:project     'com.tristanstraub/boot-file
       :version     +version+
       :description "Boot task to insert files into the target."
       :url         "https://github.com/tristanstraub/boot-file"
       :scm         {:url "https://github.com/tristanstraub/boot-file"}
       :license     {"MIT" "https://opensource.org/licenses/MIT"}})

(deftask build []
  (comp
   (pom)
   (jar)
   (install)))
