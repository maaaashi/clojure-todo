(ns todo.handler
  (:require [ring.util.response :as res]))

(defn get-todo [id]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))

(defn get-todos [req]
  (res/response [{:id 1 :title "朝食を食べる" :completed false}
                 {:id 2 :title "昼食を食べる" :completed false}
                 {:id 3 :title "夕食を食べる" :completed false}]))

(defn post-todos [req]
  (let [{:keys [title completed]} (get-in req [:body])]
    (res/response {:id 1 :title title :completed completed})))

(defn put-todo [id item]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))

(defn delete-todo [id]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))