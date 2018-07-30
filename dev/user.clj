(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh]])
  (:use [ruebik.core]
        [ruebik.heuristic]
        [ruebik.solved]
        [search.core]))

(defn demo
  ([] (demo (rotate solved-2x2 :u :f :u :u :r :b :l :l :u :f)))
  ([scrambled]
   (println "Solving " scrambled)
   (time (a* scrambled
             (partial = solved-2x2)
             (fn [x y] 1)
             (partial rotation-distance-sum solved-2x2)
             neighbors
             20))))
