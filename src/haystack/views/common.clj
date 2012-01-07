(ns haystack.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
  (html5 
   [:head
    [:title "haystack"]
    (include-css "/css/reset.css")
    (include-css "/css/haystack.css")]
   [:body
    [:div#wrapper
     content]]))
