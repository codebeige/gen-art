(ns p-1-2-3.sketch-01
  (:require [p-1-2-3.core :refer :all]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch generated-color-palettes
  :title "Color Palettes from Rules"
  :size [800 800]
  :middleware [m/fun-mode]
  :setup setup
  :draw draw
  :mouse-moved mouse-moved
  :key-pressed key-pressed)
