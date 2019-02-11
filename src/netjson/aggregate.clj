;This file is part of netjson.
;
;netjson is free software: you can redistribute it and/or modify
;it under the terms of the GNU General Public License as published by
;the Free Software Foundation, either version 3 of the License, or
;(at your option) any later version.
;
;netjson is distributed in the hope that it will be useful,
;but WITHOUT ANY WARRANTY; without even the implied warranty of
;MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;GNU General Public License for more details.
;
;You should have received a copy of the GNU General Public License
;along with netjson.  If not, see <https://www.gnu.org/licenses/>.

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