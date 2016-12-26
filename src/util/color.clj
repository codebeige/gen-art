(ns util.color
  (:require [quil.core :as q]))

(defn rgb [c]
  [(q/red c) (q/green c) (q/blue c)])

(defn greyscale [c]
  (-> (apply + (rgb c))
      (/ 3)))

(def luminosity-weights [0.21 0.72 0.07])

(defn luminosity [c]
  (-> (apply + (map * luminosity-weights (rgb c)))
      (/ 3)))
