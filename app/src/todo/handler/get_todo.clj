(ns todo.handler.get-todo
  (:require [ring.util.response :as res]
            [todo.driver.driver :as driver]))

(defn get-todo [id]
  (-> (driver/find-todo id)
      (res/response)))
