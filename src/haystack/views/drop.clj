(ns haystack.views.drop
  (:require [haystack.views.layout :as layout]
            [haystack.models.file :as file])
  (:use [noir.core :only [defpage]]))

(defpage [:post "/drop"] params
  (let [data ((keyword file/upload-label) params) 
        result (file/upload data)]
    (layout/layout
     [:div
      [:div "Uploaded!"]
      [:div [:a {:href "/"} "Go back?"]]])))
