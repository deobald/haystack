(ns haystack.views.index
  (:require [haystack.views.common :as common]
            [clojure.java.io :as io])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.form-helpers :only [form-to file-upload text-field submit-button]]))

(defpage "/" []
  (common/layout
   [:p "oh hey"]
   [:br]

   [:form {:action "/drop" :method "post" :enctype "multipart/form-data"} 
    (file-upload "file:")
    (submit-button "drop")]
   [:br]

   (form-to [:post "/find"]
            (text-field "what")
            (submit-button "find"))))

(defn upload-file [data]
  (io/copy (:tempfile data) (io/file "file.out")))

(defpage [:post "/drop"] params
  (let [data ((keyword "file:") params)]
    (common/layout
     [:pre 
      ;;(str "zig: " (keys params))
      ]
     (upload-file data)
     )))