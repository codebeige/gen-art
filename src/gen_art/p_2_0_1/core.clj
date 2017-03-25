(ns gen-art.p-2-0-1.core)

(defn offset [{:keys [width height]}]
  (map #(/ % 2) [width height]))
