(ns ruebik.solved)

(def solved-2x2
  [[:u :f :l] [:u :l :b] [:u :b :r] [:u :r :f]
   [:d :f :l] [:d :l :b] [:d :b :r] [:d :r :f]])

(def solved-3x3
  [[:u :f :l] [:u :l :b] [:u :b :r] [:u :r :f]              ; corners - top
   [:d :f :l] [:d :l :b] [:d :b :r] [:d :r :f]              ; corners - bottom
   [:u :f] [:u :l] [:u :b] [:u :r]                          ; edges - top
   [:f :l] [:l :b] [:b :r] [:r :f]                          ; edges - middle
   [:d :f] [:d :l] [:d :b] [:d :r]])                        ; edges - bottom
