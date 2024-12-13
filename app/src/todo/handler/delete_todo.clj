(ns todo.handler.delete-todo
  (:require [ring.util.response :as res]
            [todo.driver :as driver]))

(defn delete-todo [id]
  (driver/delete-todo id)
  (res/response {:message "Deleted"}))