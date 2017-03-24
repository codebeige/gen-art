(ns gen-art.p-2-0-1.def
  (:require [gen-art.p-2-0-1.sketch-01 :as sketch-01]
            [quil.core :as q]
            [quil.middleware :as m]))

(q/defsketch p-2-0-1-01
  :title "Hello Form"
  :size [550 550]
  :middleware [m/fun-mode]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved)
