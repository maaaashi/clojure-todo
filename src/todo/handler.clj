(ns todo.handler
  (:require [ring.util.response :as res]
            [todo.driver :as driver]))

(defn get-todo [id]
  (-> (driver/find-todo id)
      (res/response)))

(defn get-todos [_]
  (-> (driver/get-todos)
      (res/response)))

(defn post-todos [req]
  (let [{:keys [title completed]} (get-in req [:body])]
    (-> (driver/insert-todo title completed)
        (res/response))))

(defn put-todo [req id]
  (let [{:keys [title completed]} (get-in req [:body])]
    (-> (driver/update-todo id title completed)
        (res/response))))

(defn delete-todo [id]
  (driver/delete-todo id)
  (res/response {:message "Deleted"}))