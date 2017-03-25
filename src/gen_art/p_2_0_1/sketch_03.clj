(ns gen-art.p-2-0-1.sketch-03
  (:refer-clojure :exclude [update])
  (:require [gen-art.p-2-0-1.core :refer [offset]]
            [quil.core :as q]))

(defn setup []
  (q/color-mode :hsb 360 100 100 100)
  (q/smooth)
  (q/no-fill)
  (q/background 360)
  (q/stroke-weight 2)
  {:width (q/width)
   :height (q/height)
   :count 0
   :color [0 10]
   :radius 0
   :draw? false})

(defn update [{:keys [clear] :as state}]
  (if (= clear :pending)
    (assoc state :clear :now)
    (dissoc state :clear)))

(defn draw [{:keys [clear draw? color count radius] :as state}]
  (println state)
  (when (= clear :now)
    (q/background 360))
  (when draw?
    (apply q/translate (offset state))
    (apply q/stroke color)
    (q/begin-shape)
    (doseq [angle (range 0 q/TWO-PI (/ q/TWO-PI count))]
      (q/vertex (* (q/cos angle) radius) (* (q/sin angle) radius)))
    (q/vertex radius 0)
    (q/end-shape)))

(defn mouse-dragged [{:keys [width height] :as state} {:keys [x y]}]
  (assoc state
         :count  (-> y
                     (q/map-range 0 height 3 10)
                     q/round)
         :radius (-> x
                     (q/map-range 0
                                  width
                                  0.5
                                  (- (apply min (offset state)) 0.5)))))

(defn mouse-pressed [state _]
  (assoc state :draw? true))

(defn mouse-released [state _]
  (assoc state :draw? true))

(defn key-typed [state {:keys [key]}]
  (case key
    :1 (assoc state :color [0 10])
    :2 (assoc state :color [192 100 64 10])
    :3 (assoc state :color [52 100 71 10])
    state))

(defn key-pressed [state {:keys [key-code]}]
  (if (= 8 key-code)
    (assoc state
           :clear :pending
           :draw? false)
    state))
