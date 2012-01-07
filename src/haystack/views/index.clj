(ns haystack.views.index
  (:require [haystack.views.common :as common]
            [clojure.java.io :as io]
            [clj-time.core :as time]
            [clj-time.format :as time-format])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.form-helpers :only [form-to file-upload text-field submit-button]]))

(def upload-label "file:")

(defpage "/" []
  (common/layout
   [:p "oh hey"]
   [:br]

   [:form {:action "/drop" :method "post" :enctype "multipart/form-data"} 
    (file-upload upload-label)
    (submit-button "drop")]
   [:br]

   (form-to [:post "/find"]
            (text-field "what")
            (submit-button "find"))))

(defn timestamp []
  (time-format/unparse (time-format/formatters :basic-date-time-no-ms) (time/now)))

(defn upload-file
  "data will be in format: {:size 179,
                            :tempfile #,
                            :content-type \"application/x-desktop\",
                            :filename \"examples.desktop\"}"
  [data]
  (io/copy (:tempfile data) (io/file (str (timestamp) "-" (:filename data)))))

(defpage [:post "/drop"] params
  (let [data ((keyword upload-label) params)
        result (upload-file data)]
    (common/layout
     [:div
      [:div "Uploaded!"]
      [:div [:a {:href "/"} "Go back?"]]])))

