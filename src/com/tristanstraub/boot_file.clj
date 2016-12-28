(ns com.tristanstraub.boot-file
  (:require [me.raynes.fs :as fs]
            [clojure.java.io :as io]
            [boot.core :as core]))

(defn mkdir-parents! [file]
  (doseq [dir (fs/parents file)]
    (when-not (.exists dir)
      (.mkdir dir))))

(core/deftask file [p path STR str "File path"
               c content STR str "File content"]
  (core/with-pre-wrap fileset
    (let [tmp    (core/tmp-dir!)
          index  (io/file tmp path)]

      (mkdir-parents! index)

      (spit index content)

      (-> fileset
          (core/add-resource tmp)
          core/commit!))))
