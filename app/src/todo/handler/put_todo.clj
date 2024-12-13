(ns todo.handler.put-todo
  (:require [ring.util.response :as res]
            [todo.driver.driver :as driver]))

(defn put-todo [req id]
  (let [{:keys [title completed]} (get-in req [:body])]
    (-> (driver/update-todo id title completed)
        (res/response))))
