(ns haystack.views.welcome
  (:require [haystack.views.layout :as layout])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
  (layout/layout
   [:p "Welcome to haystack"]))
