(ns p-1-2-3.core
  (:require [quil.core :as q]))

(def step-x-min 15)
(def step-x-max 105)

(def step-y-min 25)
(def step-y-max 170)

(def color-count 63)

(defn rand-from-range
  ([end] (rand-from-range 0 end))
  ([start end]
   (rand-nth (range start (inc end)))))

(defn rand-val [{:keys [min max] :or {min 0, max 100}}]
  (+ min (rand (- max min))))

(defn rand-color [{:keys [h s b] :or {h {:max 360}}}]
  [(rand-val h) (rand-val s) (rand-val b) 100])

(defn rand-colors
  ([] (rand-colors {}))
  ([& color-ranges]
   (->> color-ranges
        (map #(repeatedly (partial rand-color %)))
        (apply interleave)
        (take color-count))))

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (q/color-mode :hsb 360 100 100 100)
  {:step-x 60
   :step-y 100
   :width (q/width)
   :height (q/height)
   :colors (rand-colors)})

(defn origins [{:keys [step-x step-y width height]}]
  (for [y (range 0 height step-y)
        x (range 0 width step-x)]
    {:x x :y y}))

(defn tiles [{:keys [colors] :as state}]
  (map #(assoc %1 :color %2) (origins state) (cycle colors)))

(defn draw [{:keys [step-x step-y] :as state}]
  (doseq [{:keys [x y color]} (tiles state)]
    (apply q/fill color)
    (q/rect x y step-x step-y)))

(defn mouse-moved [{:keys [width height] :as state} {:keys [x y]}]
  (assoc state
         :step-x
         (q/map-range x 0 width step-x-min step-x-max)
         :step-y
         (q/map-range y 0 height step-y-min step-y-max)))

(defn key-pressed [{:keys [colors] :as state} {:keys [key]}]
  (assoc state
         :colors
         (apply
          rand-colors
          (case key
            :1 [{}]
            :2 [{:b {:min 100}}]
            :3 [{:s {:min 100}}]
            :4 [{:h {:max 0} :s {:max 0}}]
            :5 [{:h {:min 195 :max 195} :s {:min 100}}]
            :6 [{:h {:min 195 :max 195} :b {:min 100}}]
            :7 [{:h {:max 180} :s {:min 80} :b {:min 50 :max 90}}]
            :8 [{:h {:min 180} :s {:min 80} :b {:min 50 :max 90}}]
            :9 [{:s {:min 100}}
                {:h {:min 195 :max 195} :b {:min 100}}]
            :0 [{:h {:min 192 :max 192} :b {:min 10}}
                {:h {:min 273 :max 273} :b {:min 10}}]))))
