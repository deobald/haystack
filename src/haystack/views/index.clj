(ns haystack.views.index
  (:require [haystack.views.layout :as layout]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.java.shell :as shell]
            [clj-time.core :as time]
            [clj-time.format :as time-format])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers :only [image]]
        [hiccup.form-helpers :only [form-to file-upload text-field submit-button]])
  (:import [java.lang String]))

(def upload-label "file:")

(defpage "/" []
  (layout/layout
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
    (layout/layout
     [:div
      [:div "Uploaded!"]
      [:div [:a {:href "/"} "Go back?"]]])))

(defn files []
  (:out (shell/sh "ls" "-1" "files")))

(defn find-files [what]
  (s/split)
  ;;(shell/sh "find" "files" "-name" (str "'*" what "*'"))
  )

(defpage [:post "/find"] {what :what}
  (let [results (find-files what)]
    (layout/layout
     [:div
      [:div "Found:"]
      (for [r results]
        [:div r])])))

