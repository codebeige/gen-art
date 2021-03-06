(ns gen-art.p-2-0-1.def
  (:require [gen-art.p-2-0-1.sketch-01 :as sketch-01]
            [gen-art.p-2-0-1.sketch-02 :as sketch-02]
            [gen-art.p-2-0-1.sketch-03 :as sketch-03]
            [quil.core :as q]
            [quil.middleware :as m]))

#_(q/defsketch p-2-0-1-01
  :title "Hello Form"
  :size [550 550]
  :middleware [m/fun-mode]
  :setup sketch-01/setup
  :draw sketch-01/draw
  :mouse-moved sketch-01/mouse-moved)

#_(q/defsketch p-2-0-1-02
  :title "Hello Form"
  :size [720 720]
  :middleware [m/fun-mode]
  :setup sketch-02/setup
  :draw sketch-02/draw
  :mouse-dragged sketch-02/mouse-dragged
  :mouse-pressed sketch-02/mouse-pressed
  :mouse-released sketch-02/mouse-released)

(q/defsketch p-2-0-1-03
  :title "Hello Form"
  :size [720 720]
  :middleware [m/fun-mode]
  :setup sketch-03/setup
  :update sketch-03/update
  :draw sketch-03/draw
  :mouse-dragged sketch-03/mouse-dragged
  :mouse-pressed sketch-03/mouse-pressed
  :mouse-released sketch-03/mouse-released
  :key-typed sketch-03/key-typed
  :key-pressed sketch-03/key-pressed)
