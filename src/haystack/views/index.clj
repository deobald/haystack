(ns haystack.views.index
  (:require [haystack.views.common :as common]
            [clojure.java.io :as io]
            [clojure.java.shell :as shell]
            [clj-time.core :as time]
            [clj-time.format :as time-format])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers :only [image]]
        [hiccup.form-helpers :only [form-to file-upload text-field submit-button]]))

(def upload-label "file:")

(defpage "/" []
  (common/layout
   [:div
    [:div#dropper
     [:form {:action "/drop" :method "post" :enctype "multipart/form-data"}
      [:div (file-upload upload-label)]
      [:div (submit-button {:class "btn primary"} "drop")]]]
    [:div#logo
     (image "/img/haystack.jpg" "HAYSTACK!")]
    [:div#finder
     (form-to [:post "/find"]
              [:div (text-field "what")]
              [:div (submit-button {:class "btn primary"} "find")])]]))

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

(defn find-files [what]
  (shell/sh "find" "files" "-name" (str "'*" what "*'")))

(defpage [:post "/find"] {what :what}
  (let [results (find-files what)]
    (common/layout
     [:div
      [:div "Found:"]
      (for [r results]
        [:div r])])))