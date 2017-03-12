(ns gen-art.p-1-1-2.def
  (:require [gen-art.p-1-1-2.sketch-01 :as sketch-01]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch p-1-1-2-01
  :title "Sectored Color Space"
  :size [800 800]
  :middleware [m/fun-mode m/pause-on-error]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved
  :key-pressed sketch-01/key-pressed)
