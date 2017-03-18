(ns gen-art.util.color
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

(defn rand-val [{:keys [min max] :or {min 0, max 100}}]
  (+ min (rand (- max min))))

(defn rand-color [{:keys [h s b] :or {h {:max 360}}}]
  [(rand-val h) (rand-val s) (rand-val b) 100])

(defn rand-colors
  ([] (rand-colors {}))
  ([& color-ranges]
   (->> color-ranges
        (map #(repeatedly (partial rand-color %)))
        (apply interleave))))

