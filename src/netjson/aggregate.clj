(ns netjson.aggregate
  (:require [cheshire.core :refer [parse-string]]
            [clojure.java.io :as io]))

(def data (io/resource "netjson.json"))

(defn get-data [path]
  (parse-string (slurp path) keyword))

(defn sub-stat [data]
  (-> data
      (select-keys [:name :uptime])
      (assoc :statistics
             (select-keys (:statistics data)
                          [:collisions :rx_frame_errors :rx_packets :tx_packets]))))

(defn collision-stat [json]
  (->> json
       (:interfaces)
       (filter #(pos? (:collisions (:statistics %))))
       (map sub-stat)
       (vec)))

(collision-stat (get-data data))