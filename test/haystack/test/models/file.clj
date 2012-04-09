(ns haystack.test.models.file
  (:require [haystack.models.file :as file])
  (:use [clojure.test]))

(deftest returns-all-files
  (is (= #{"nothing" "one" "two" "three" "four" "five" "six"}
         (set (file/ls)))))

(deftest finds-specific-file
  (is (= ["five"]
         (file/find "five"))))

(deftest finds-files-with-the-letter-oh
  (is (= #{"nothing" "one" "two" "four"}
         (set (file/find "o")))))