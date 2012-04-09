(ns haystack.views.index
  (:require [haystack.views.layout :as layout]
            [haystack.models.file :as file])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers :only [image]]
        [hiccup.form-helpers :only [form-to file-upload text-field submit-button]]))

(defpage "/" []
  (layout/layout
   [:div
    [:div#dropper
     [:form {:action "/drop" :method "post" :enctype "multipart/form-data"}
      [:div (file-upload file/upload-label)]
      [:div (submit-button {:class "btn primary"} "drop")]]]
    [:div#logo
     (image "/img/haystack.jpg" "HAYSTACK!")]
    [:div#finder
     (form-to [:post "/find"]
              [:div (text-field "what")]
              [:div (submit-button {:class "btn primary"} "find")])]]))
