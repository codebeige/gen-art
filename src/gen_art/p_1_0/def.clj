(ns gen-art.p-1-0.def
  (:require [gen-art.p-1-0.sketch-01 :as sketch-01]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch p-1-0-01
  :title "Hello Color"
  :size [720 720]
  :middleware [m/fun-mode m/pause-on-error]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved)
