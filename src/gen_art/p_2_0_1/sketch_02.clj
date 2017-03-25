(ns gen-art.p-2-0-1.sketch-02
  (:require [gen-art.p-2-0-1.core :refer [offset]]
            [quil.core :as q]))

(defn setup []
  (q/no-fill)
  (q/background 255)
  (q/stroke 0, 25)
  (q/stroke-weight 2)
  {:width (q/width)
   :height (q/height)
   :count 35
   :draw? false})

(defn draw [{:keys [draw? count radius] :as state}]
  (when draw?
    (apply q/translate (offset state))
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
