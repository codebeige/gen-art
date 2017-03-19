(ns gen-art.p-1-2-3.def
  (:require [gen-art.p-1-2-3.sketch-01 :as sketch-01]
            [gen-art.p-1-2-3.sketch-02 :as sketch-02]
            [gen-art.p-1-2-3.sketch-03 :as sketch-03]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch p-1-2-3-01
  :title "Color Palettes from Rules"
  :size [800 800]
  :middleware [m/fun-mode]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved
  :key-pressed sketch-01/key-pressed)

(q/defsketch p-1-2-3-02
  :title "Color Palettes from Rules"
  :size [800 800]
  :middleware [m/fun-mode]
  :setup sketch-02/setup
  :draw sketch-02/draw
  :mouse-released sketch-02/mouse-released)

(q/defsketch p-1-2-3-03
  :title "Color Palettes from Rules"
  :size [800 800]
  :middleware [m/fun-mode]
  :renderer :p3d
  :setup sketch-03/setup
  :draw sketch-03/draw
  :mouse-released sketch-03/mouse-released)
