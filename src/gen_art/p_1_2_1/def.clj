(ns gen-art.p-1-2-1.def
  (:require [gen-art.p-1-2-1.sketch-01 :as sketch-01]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch p-1-2-1-01
  :title "Interpolated Color Palettes"
  :size [800 800]
  :middleware [m/fun-mode]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved
  :mouse-clicked sketch-01/mouse-clicked
  :key-typed sketch-01/key-typed)
