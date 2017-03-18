(ns gen-art.p-1-2-3.sketch-02
  (:require [gen-art.util.color :as color]
            [quil.core :as q]))

(def color-count 20)

(def color-ranges
  [{:s {:min 100}}
   {:h {:min 195 :max 195} :b {:min 100}}])

(def row-count-range (range 5 40))

(def fragment-variation {:min 2 :max 20})

(def subfragment-probability 0.075)

(def subfragment-variation {:min 0.1 :max 2})

(defn rand-colors []
  (->> color-ranges
       (apply color/rand-colors)
       (take color-count)))

(defn rand-float [{:keys [min max]}]
  (+ min (rand (- max min))))

(defn subfragments? []
  (< (rand) subfragment-probability))

(defn rand-fragments [n]
  (->> (repeatedly n
                   (fn []
                     (if (subfragments?)
                       (repeatedly (rand-float fragment-variation)
                                   (fn [] (rand-float subfragment-variation)))
                       [(rand-float fragment-variation)])))
       (apply concat)))

(defn rand-steps [n width]
  (let [fragments (rand-fragments n)
        total (reduce + fragments)]
    (map #(q/map-range % 0 total 0 width) fragments)))

(defn reset [{:keys [width height] :as state}]
  (let [rows (rand-nth row-count-range)]
    (assoc state
           :colors
           (rand-colors)
           :step-y
           (/ height rows)
           :steps-x
           (map #(rand-steps (inc %) width) (range rows)))))

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (q/color-mode :hsb 360 100 100 100)
  (reset {:width (q/width) :height (q/height)}))

(defn tiles [{:keys [steps-x step-y colors]}]
  (->> steps-x
       (map-indexed
        (fn [index values]
          (reduce
           (fn [result value]
             (conj
              result
              {:x (reduce + (-> (last result) (select-keys [:x :width]) vals))
               :y (* index step-y)
               :width value
               :height step-y}))
           []
           values)))
       (apply concat)
       (map #(assoc %2 :color %1) (cycle colors))))

(defn draw [{:keys [step-y steps-x] :as state}]
  (doseq [{:keys [x y width height color]} (tiles state)]
    (apply q/fill color)
    (q/rect x y width height)))

(defn mouse-released [state _]
  (reset state))
