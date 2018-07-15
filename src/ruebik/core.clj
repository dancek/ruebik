(ns ruebik.core)

(def fwd-ring [:u :f :l :d :b :r])

(defn ring-for-face
  [face]
  (let [fwd-ring' (cycle fwd-ring)
        idx       (.indexOf fwd-ring face)
        ring      (concat (take 2 (drop (+ idx 1) fwd-ring'))
                          (take 2 (drop (+ idx 4) fwd-ring')))]
    (if (even? idx)
      ring
      (reverse ring))))

(defn rotations-for-face
  [face]
  (let [ring         (ring-for-face face)
        same-map     (zipmap fwd-ring fwd-ring)
        rotation-map (zipmap ring (rest (cycle ring)))]
    (merge same-map rotation-map)))

(defn rotate-piece
  [face]
  (let [rotations (rotations-for-face face)]
    (fn [piece]
      (if (.contains piece face)
        (mapv #(get rotations %) piece)
        piece))))

(defn rotate
  "Do a rotation, eg. (rotate cube :u)"
  [cube face]
  (mapv (rotate-piece face) cube))

(def solved-2x2
  [[:u :f :l] [:u :l :b] [:u :b :r] [:u :r :f]
   [:d :f :l] [:d :l :b] [:d :b :r] [:d :r :f]])

(def solved-3x3
  [[:u :f :l] [:u :l :b] [:u :b :r] [:u :r :f]              ; corners - top
   [:d :f :l] [:d :l :b] [:d :b :r] [:d :r :f]              ; corners - bottom
   [:u :f] [:u :l] [:u :b] [:u :r]                          ; edges - top
   [:f :l] [:l :b] [:b :r] [:r :f]                          ; edges - middle
   [:d :f] [:d :l] [:d :b] [:d :r]])                        ; edges - bottom


;;; TODO:
; - check if solved
; - counterclockwise rotations
; - actual solving, argh
