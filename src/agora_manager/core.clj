(ns agora-manager.core
  (:require [clojure.data.json :as json]
            [java-time :refer [local-date]]
            [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn get-date []
  (->> (str/split (.toString (local-date)) #"-")
       (zipmap [:year :month :day])))

(defn init-directories [user {y :year m :month d :day}]
  (let [dirs [user "contents" y m d]]
    (loop [d dirs path ""]
      (if (empty? d) (println "directories created.")
        (let [p (str path (first d) "/")]
          (.mkdir (io/file p))
          (recur (rest d) p))))))

(defn -main
  "I don't do a whole lot ... yet."
  [user-name]
  (init-directories user-name (get-date)))
