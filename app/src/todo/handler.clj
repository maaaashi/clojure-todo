(ns todo.handler
  (:require [todo.driver :as driver]
            [ring.util.response :as res]))

(defn get-todos [_]
  (-> (driver/get-todos)
      (res/response)))

(defn get-todo [id]
  (-> (driver/find-todo id)
      (or (throw (ex-info (str "Todo id: " id ", Not found") {:status 404})))
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