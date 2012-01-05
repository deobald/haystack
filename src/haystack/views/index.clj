(ns haystack.views.index
  (:require [haystack.views.common :as common]
            [clojure.java.io :as io])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.form-helpers :only [form-to file-upload text-field submit-button]]))

(defpage "/" []
  (common/layout
   [:p "oh hey"]
   (form-to [:post "/drop"]
            (file-upload "Your file:")
            (submit-button "drop"))
   (form-to [:post "/find"]
            (text-field "what")
            (submit-button "find"))))

(defn upload-file
  [data]
  (:tempfile data)
  ;;(io/copy (file :tempfile) (io/file-str "file.out"))
  )

(defpage [:post "/drop"] [params]
  ;;{:keys [file]}
  (common/layout
   [:pre (str "zig" ;;(keys params)
              ;;(upload-file file)
              )]))