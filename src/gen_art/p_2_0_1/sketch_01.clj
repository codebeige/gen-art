(ns gen-art.p-2-0-1.sketch-01
  (:require [quil.core :as q]))

(defn offset [{:keys [width height]}]
  (map #(/ % 2) [width height]))

(defn setup []
  (q/no-cursor)
  (q/no-fill)
  (q/stroke-cap :square)
  {:width (q/width)
   :height (q/height)
   :count 35
   :weight 10
   :radius 100})


(defn draw [{:keys [weight count radius] :as state}]
  (q/background 255)
  (q/stroke-weight weight)
  (apply q/translate (offset state))
  (q/begin-shape)
  (doseq [angle (range 0 q/TWO-PI (/ q/TWO-PI count))]
    (q/line 0 0 (* (q/cos angle) radius) (* (q/sin angle) radius)))
  (q/end-shape))

(defn mouse-moved [{:keys [width height] :as state} {:keys [x y]}]
  (let [[offset-x offset-y] (offset state)]
    (assoc state
           :count  (-> y
                       (q/map-range 0 height 2 80)
                       q/round)
           :radius (-> x
                       (q/map-range 0
                                    width
                                    0.5
                                    (- (min offset-x offset-y) 0.5)))
           :weight (/ y 20))))
