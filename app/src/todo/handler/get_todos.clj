(ns todo.handler.get-todos
  (:require [ring.util.response :as res]
            [todo.driver :as driver]))

(defn get-todos [_]
  (-> (driver/get-todos)
      (res/response)))
