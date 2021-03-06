(ns build
  (:require [mechtron.home :refer [home]]
            [mechtron.company :refer [company]]
            [mechtron.products :refer [products]]
            [mechtron.capabilities :refer [capabilities]]
            [mechtron.contact :refer [contact]]
            [sass.core :refer :all]
            :reload-all))

(def pages {"index" home
            "company" company
            "products" products
            "capabilities" capabilities
            "contact" contact})

(defn css
  []
  (println "Writing CSS to dist/static/css/style.css\n")
  (spit "dist/static/css/style.css"
    (render-file-path "resources/scss/style.scss"
                      :property-syntax :new :style :compressed)))

(defn html
  []
  (println "Writing HTML to dist/*.htm")
  (doseq [[filename render-page] pages]
    (println (str "  -> Wrote dist/" filename ".htm"))
    (spit (str "dist/" filename ".htm")
      (str
        "<!doctype html>\n"
        (render-page))))
  (println "\n"))

(defn -main
  []
  (html)
  (css))
