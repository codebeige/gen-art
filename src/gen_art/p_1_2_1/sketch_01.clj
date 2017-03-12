(ns gen-art.p-1-2-1.sketch-01
  (:require [quil.core :as q]))

(def max-rows 12)
(def max-cols 64)

(defn- rand-val
  ([] (rand-val 0 100))
  ([end] (rand-val 0 end))
  ([start end] (rand-nth (range start (inc end)))))

(defn- rand-color [& ranges]
  (map #(if (vector? %) (apply rand-val %) %) ranges))

(defn- color-pairs []
  (repeatedly #(list (rand-color [60] [100] 100)
                     (rand-color [160 190] 100 [100]))))

(defn- rand-colors
  ([] (rand-colors max-rows))
  ([n] (doall (take n (color-pairs)))))

(defn- color-mode!
  ([] (color-mode! :hsb))
  ([mode]
   (case mode
     :rgb (q/color-mode :rgb)
     :hsb (q/color-mode :hsb 360 100 100))))

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (color-mode!)
  {:mode :hsb
   :colors (rand-colors)
   :steps-x max-cols
   :steps-y max-rows})

(defn- grid [step-x step-y width height]
  (for [y (range 0 height step-y)
        x (range 0 width step-x)]
    {:x x :y y}))

(defn- dimensions [width height]
  (repeat {:width width, :height height}))

(defn- interpolate [c1 c2 steps step]
  (q/lerp-color c1 c2 (q/norm step 0 (dec steps))))

(defn- with-color-mode [mode coll]
  (color-mode! mode)
  (let [result (doall coll)]
    (color-mode!)
    result))

(defn- color-range [[start end] steps mode]
  (let [c1 (apply q/color start)
        c2 (apply q/color end)]
    (with-color-mode mode
      (map #(hash-map :color (interpolate c1 c2 steps %)) (range steps)))))

(defn- color-ranges [pairs steps mode]
  (mapcat #(color-range % steps mode) pairs))

(defn- patches [{:keys [mode colors steps-x steps-y width height]}]
  (let [step-x (/ width steps-x)
        step-y (/ height steps-y)]
    (map merge
         (grid step-x step-y width height)
         (dimensions step-x step-y)
         (color-ranges colors steps-x mode))))

(defn draw [state]
  (doseq [{:keys [color x y width height]}
          (patches (merge state {:width (q/width) :height (q/height)}))]
    (q/fill color)
    (q/rect x y width height)))

(defn mouse-moved [state {:keys [x y]}]
  (assoc
   state
   :steps-x (q/round (q/map-range x 0 (q/width) 2 max-cols))
   :steps-y (q/round (q/map-range y 0 (q/height) 2 max-rows))))

(defn mouse-clicked [state {:keys [button]}]
  (case button
    :left (assoc state :colors (rand-colors))
    state))

(defn key-typed [state {:keys [key] :as event}]
  (case key
    :1 (assoc state :mode :hsb)
    :2 (assoc state :mode :rgb)
    state))
