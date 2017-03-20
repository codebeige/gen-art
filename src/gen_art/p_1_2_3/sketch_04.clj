(ns gen-art.p-1-2-3.sketch-04
  (:require [gen-art.util.color :as color]
            [quil.core :as q]))

(def color-count 20)

(def color-ranges
  [{:s {:min 100} :a {:min 35 :max 35}}
   {:h {:min 195 :max 195} :b {:min 100} :a {:min 35 :max 35}}])

(def row-count-range (range 5 40))

(def fragment-variation {:min 2 :max 20})

(def subfragment-probability 0.075)

(def subfragment-variation {:min 0.1 :max 2})

(def visibility-propability 0.45)

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

(defn visible? []
  (< (rand) visibility-propability))

(defn reset [{:keys [width height] :as state}]
  (let [rows (rand-nth row-count-range)
        steps-x (map #(rand-steps (inc %) width) (range rows))]
    (assoc state
           :colors
           (rand-colors)
           :step-y
           (/ height rows)
           :steps-x
           steps-x
           :visibilities
           (repeatedly (reduce #(+ %1 (count %2)) 0 steps-x) visible?))))

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (q/color-mode :hsb 360 100 100 100)
  (reset {:width (q/width) :height (q/height)}))

(defn tiles [{:keys [steps-x step-y colors visibilities]}]
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
               :height (* 1.5 step-y)}))
           []
           values)))
       (apply concat)
       (map #(assoc %3 :color %1 :visible? %2) (cycle colors) visibilities)
       (reverse)))

(defn draw [{:keys [step-y steps-x] :as state}]
  (q/background 0)
  (doseq [{:keys [x y width height color visible?]} (tiles state)]
    (when visible?
      (let [x2 (+ x width)
            y2 (+ y height)]
        (q/begin-shape)
        (q/fill 0)
        (q/vertex x y)
        (q/vertex x2 y)
        (apply q/fill color)
        (q/vertex x2 y2)
        (q/vertex x y2)
        (q/end-shape :close)))))

(defn mouse-released [state _]
  (reset state))
