(ns haystack.views.welcome
  (:require [haystack.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
  (common/layout
   [:p "Welcome to haystack"]))
