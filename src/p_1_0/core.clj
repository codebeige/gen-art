(ns p-1-0.core
  (:require [quil.core :refer :all]
            [quil.middleware :as m]))

(defn setup []
  {:x 100 :y 100 :r 100})

(defn draw [state]
  (ellipse 100 140 50 50))

(defsketch hello-color
  :title "Hello Color"
  :size [720 720]
  :setup setup
  :draw draw
  :middleware [m/fun-mode])
