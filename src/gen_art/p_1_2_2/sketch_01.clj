(ns gen-art.p-1-2-2.sketch-01
  (:require [gen-art.util.color :as c]
            [quil.core :as q]))

(def step-min 5)
(def cols-min 3)

(def sort-modes {:none nil
                 :hue q/hue
                 :saturation q/saturation
                 :brightness q/brightness
                 :greyscale c/greyscale
                 :luminosity c/luminosity})

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  {:image (q/load-image "img/donuts.png")
   :step step-min
   :width (q/width)
   :height (q/height)
   :mode :none})

(defn- color-at [image x y]
  (.get image (* x (.-width image)) (* y (.-height image))))

(defn- tiles [{:keys [image step width height]}]
  (for [y (range 0 height step)
        x (range 0 width step)]
    {:x x
     :y y
     :color (color-at image
                      (q/norm x 0 width)
                      (q/norm y 0 height))}))

(defn- sort-tiles [tiles mode]
  (if-let [colorfn (get sort-modes mode)]
    (map #(assoc %1 :color %2)
         tiles
         (sort-by colorfn (map :color tiles)))
    tiles))

(defn draw [{:keys [step mode] :as state}]
  (doseq [{:keys [x y color]} (-> (tiles state) (sort-tiles mode))]
    (q/fill color)
    (q/rect x y step step)))

(defn- step-max [width]
  (/ width cols-min))

(defn- step-at [x width]
  (-> (q/map-range x 0 width step-min (step-max width))
      (q/round)))

(defn mouse-moved
  "Resolution depends on horizontal mouse position."
  [{:keys [width] :as state} {:keys [x]}]
  (assoc state :step (step-at x width)))

(defn key-pressed
  "Keys 1-4 switch between sort modes."
  [state {:keys [key]}]
  (if (#{:1 :2 :3 :4 :5 :6} key)
    (assoc state
           :mode
           (case key
             :1 :none
             :2 :hue
             :3 :saturation
             :4 :brightness
             :5 :greyscale
             :6 :luminosity))
    state))
