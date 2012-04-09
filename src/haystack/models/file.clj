(ns haystack.models.file
  (:require [clj-time.core :as time]
            [clj-time.format :as time-format]
            [clojure.java.io :as io]
            [clojure.string :as s])
  (:import [java.io File]))

(def upload-label "file:")
(def upload-dir "files")

(defn timestamp []
  (time-format/unparse (time-format/formatters :basic-date-time-no-ms) (time/now)))

(defn upload
  "data will be in format: {:size 179,
                            :tempfile #,
                            :content-type \"application/x-desktop\",
                            :filename \"examples.desktop\"}"
  [data]
  (io/copy (:tempfile data)
           (io/file (str upload-dir
                         "/"
                         (timestamp)
                         "-"
                         (:filename data)))))

(defn ls []
  (.list (File. upload-dir)))

(defn name-like [what]
  (fn [f] (.contains f what)))

(defn find [what]
  (filter (name-like what) (ls)))
