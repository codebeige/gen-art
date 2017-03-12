(ns gen-art.p-1-2-2.def
  (:require [gen-art.p-1-2-2.sketch-01 :as sketch-01]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch p-1-2-2-01
  :title "Color Palettes from Images"
  :size [600 600]
  :middleware [m/fun-mode]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved
  :key-pressed sketch-01/key-pressed)
