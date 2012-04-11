(ns haystack.views.layout
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
  (html5 
   [:head
    [:title "haystack"]
    [:script {:src "/js/vendor/jquery-1.6.4.min.js"}]
    [:script {:src "/js/vendor/underscore-min.js"}]
    [:script {:src "/js/notifications.js"}]
    [:script {:src "/js/uploader.js"}]
    [:script {:src "/js/files.js"}]
    (include-css "/css/reset.css")
    (include-css "/css/haystack.css")]
   [:body
    [:div#wrapper
     content]]))

(comment
  (a b c (x y z) d e f))

;; 1) paredit and paren-match colours for demos, plz. kthx.
;; 2) going straight-edge for lent