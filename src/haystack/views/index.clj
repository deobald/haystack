(ns haystack.views.index
  (:require [haystack.views.layout :as layout]
            [haystack.models.file :as file])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers :only [image]]
        [hiccup.form-helpers :only [form-to
                                    file-upload
                                    text-field
                                    submit-button]]))

(defn dropper []
  [:div#dropper
   [:form {:action "/drop" :method "post" :enctype "multipart/form-data"}
    [:div (file-upload file/upload-label)]
    [:div (submit-button {:class "btn primary"} "drop")]]])

(defn finder []
  [:div#finder
   (form-to [:post "/find"]
            [:div (text-field "what")]
            [:div (submit-button {:class "btn primary"} "find")])])

(defn file-list []
  (for [f (file/ls)]
      [:div.file f]))

(defpage "/" []
  (layout/layout
   [:div
    (dropper)
    (finder)]
   [:div.file-list
    (file-list)]))
