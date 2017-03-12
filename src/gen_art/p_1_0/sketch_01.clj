(ns gen-art.p-1-0.sketch-01
  (:refer-clojure :exclude [complement])
  (:require [quil.core :as q]))

(def max-hue 360)

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (q/color-mode :hsb max-hue 100 100)
  {:fg 0
   :bg 180
   :size 360})

(defn vertical-position [y]
  (q/norm y 0 (q/height)))

(defn complement [hue]
  (- max-hue hue))

(defn mouse-moved [state {:keys [x y]}]
    (let [hue (* (vertical-position y) max-hue)]
    (assoc
     state
     :fg (complement hue)
     :bg hue
     :size (inc x))))

(defn center []
  {:x (/ (q/width) 2)
   :y (/ (q/height) 2)})

(defn centered-square [size]
  (q/rect-mode :center)
  (let [{:keys [x y]} (center)]
    (q/rect x y size size)))

(defn draw [{:keys [fg bg size]}]
  (q/background bg 100 100)
  (q/fill fg 100 100)
  (centered-square size))
