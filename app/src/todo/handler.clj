(ns todo.handler
  (:require [todo.driver :as driver]
            [ring.util.response :as res]))

(defn get-todos [_]
  (-> (driver/get-todos)
      (res/response)))

; もしtodoがなければex-infoの例外を投げる
(defn get-todo [id]
  (-> (driver/find-todo id)
      (or (throw (ex-info "Not found" {:status 404})))
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