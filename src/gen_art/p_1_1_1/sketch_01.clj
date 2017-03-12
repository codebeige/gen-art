(ns gen-art.p-1-1-1.sketch-01
  (:refer-clojure :exclude [complement])
  (:require [quil.core :as q]))

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (q/fill 255)
  (q/color-mode :hsb 1 1 1)
  {:step-x 20 :step-y 25})

(defn mouse-moved [state {:keys [x y]}]
  (assoc
   state
   :step-x (max 2 x)
   :step-y (max 2 y)))

(defn hue-at [x]
  (q/norm x 0 (q/width)))

(defn saturation-at [y]
  (q/norm y (q/height) 0))

(defn draw [{:keys [step-x step-y]}]
  (doseq [x (range 0 (q/width) step-x)
          y (range 0 (q/height) step-y)]
    (q/fill (hue-at x) (saturation-at y) 1)
    (q/rect x y step-x step-y)))
