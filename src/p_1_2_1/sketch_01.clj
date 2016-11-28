(ns p-1-2-1.sketch-01
  (:require [p-1-2-1.core :refer :all]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch interpolated-color-palettes
  :title "Interpolated Color Palettes"
  :size [800 800]
  :middleware [m/fun-mode]
  :setup setup
  :draw draw
  :mouse-moved mouse-moved
  :mouse-clicked mouse-clicked
  :key-typed key-typed)
