(ns ruebik.heuristic)

(defn unsolved-pieces-count
  "A very naive heuristic: count of unsolved pieces."
  [solved cube]
  (count (map not= solved cube)))


(defn piece-rotation-distance
  [solved-piece piece]
  "Try to model the distance of a piece to its solved state.

  E.g. a piece that should be [:u :f :r] has the following possible cases:
  - it is solved [:u :f :r]
  - it is on the correct face [:u * *] or [* :f *] or [* * :r]
    - it is one rotation away from solved, eg. [:u :r :b]
    - it is two rotations (U2 etc) away, eg. [:u :b :l]
  - other (assume three rotations, though there are cases where two suffice)"
  (if (= solved-piece piece)
    0
    (if (not-any? true? (map = solved-piece piece))
      3
      (count (clojure.set/difference (set solved-piece) (set piece))))))


(defn rotation-distance-sum
  "Sum of piece-rotation-distance for each piece"
  [solved cube]
  (reduce + (map piece-rotation-distance solved cube)))

