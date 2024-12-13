(ns todo.handler.post-todo
  (:require [ring.util.response :as res]
            [todo.driver :as driver]))

(defn post-todos [req]
  (let [{:keys [title completed]} (get-in req [:body])]
    (-> (driver/insert-todo title completed)
        (res/response))))
