(ns gen-art.p-1-1-2.sketch-01
  (:require [quil.core :as q]))

(def tau q/TWO-PI)

(defn setup []
  (q/no-cursor)
  (q/no-stroke)
  (q/fill 255)
  (q/color-mode :hsb tau 1 1)
  {:steps 24 :saturation 1 :brightness 1})

(defn saturation-at [x]
  (q/norm x 0 (q/width)))

(defn brightness-at [y]
  (q/norm y 0 (q/height)))

(defn mouse-moved [state {:keys [x y]}]
  (assoc state
         :saturation (saturation-at x)
         :brightness (brightness-at y)))

(def mapping {:1 360, :2 45, :3 24, :4 12, :5 6})

(defn key-pressed [state {:keys [key]}]
  (if-let [steps (key mapping)]
    (assoc state :steps steps)
    state))

(defn clear []
  (q/background 0 0 1))

(defn center []
  [(/ (q/width) 2) (/ (q/height) 2)])

(defn radius []
  (* 0.45 (min (q/width) (q/height))))

(defn point-on-circle [angle]
  (map (partial * (radius)) [(q/cos angle) (q/sin angle)]))

(defn angle-seq [steps]
  (iterate (partial + (/ tau steps)) 0))

(defmacro with-shape [type & body]
  `(do (q/begin-shape ~type)
       ~@body
       (q/end-shape)))

(defn draw [{:keys [steps saturation brightness]}]
  (clear)
  (q/translate (center))
  (with-shape :triangle-fan
    (q/vertex 0 0)
    (doseq [angle (take (inc steps) (angle-seq steps))]
      (q/fill angle saturation brightness)
      (apply q/vertex (point-on-circle angle)))))
