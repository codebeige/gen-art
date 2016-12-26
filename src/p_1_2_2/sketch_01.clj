(ns p-1-2-2.sketch-01
  (:require [p-1-2-2.core :refer :all]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch interpolated-color-palettes
  :title "Color Palettes from Images"
  :size [600 600]
  :middleware [m/fun-mode]
  :setup setup
  :draw draw
  :mouse-moved mouse-moved
  :key-pressed key-pressed)
