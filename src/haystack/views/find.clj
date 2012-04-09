(ns haystack.views.find
  (:require [haystack.views.layout :as layout]
            [haystack.models.file :as file])
  (:use [noir.core :only [defpage]]))

(defpage [:post "/find"] {what :what}
  (let [results (file/find what)]
    (layout/layout
     [:div
      [:div "Found:"]
      (for [r results]
        [:div r])])))
