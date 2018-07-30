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
  "Do one or many rotations, eg. (rotate cube :u :f :f :d)"
  ([cube face] (mapv (rotate-piece face) cube))
  ([cube face & faces] (reduce rotate cube (cons face faces))))


(defn neighbors
  "Neighbors of a cube position, ie. ones that can be reached with a single rotation.

  We consider quarter rotations in both directions but not half rotations."
  [cube]
  (let [rot-fwd (map #(rotate cube %) fwd-ring)
        rot-2   (map rotate rot-fwd fwd-ring)
        rot-bwd (map rotate rot-2 fwd-ring)]
    (concat rot-fwd rot-bwd)))

(defn scramble
  ([cube] (scramble cube 100))
  ([cube depth]
   (if (= depth 0)
     cube
     (recur (rand-nth (neighbors cube)) (dec depth)))))
