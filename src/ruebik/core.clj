(ns ruebik.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def solved-2x2
  [[:u :f :l] [:u :l :b] [:u :b :r] [:u :r :f]
   [:d :f :l] [:d :l :b] [:d :b :r] [:d :r :f]])

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
        (map #(get rotations %) piece)
        piece))))

(defn rotate
  "Do a rotation, eg. (rotate cube :u)"
  [cube face]
  (map (rotate-piece face) cube))
