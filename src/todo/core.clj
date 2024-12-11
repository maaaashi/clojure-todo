(ns todo.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as res]
            [ring.middleware.json :refer [wrap-json-response]]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.route :as route]))

(defn- get-todo [id]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))

(defn- get-todos [req]
  (res/response [{:id 1 :title "朝食を食べる" :completed false}
                 {:id 2 :title "昼食を食べる" :completed false}
                 {:id 3 :title "夕食を食べる" :completed false}]))

(defn- post-todos [req]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))

(defn- put-todo [id item]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))

(defn- delete-todo [id]
  (res/response {:id 1 :title "朝食を食べる" :completed false}))

(defroutes handler'
  (POST "/v1/todos" req post-todos)
  (GET "/v1/todos" [] get-todos)
  (GET "/v1/todos/:id" [id] (get-todo id))
  (PUT "/v1/todos/:id/:item" [id item] (put-todo id item))
  (DELETE "/v1/todos/:id" [id] (delete-todo id))
  (route/not-found (res/response {:message "Not Found"})))

(def handler
  (-> handler'
      (wrap-json-response)))

(defn -main []
  (jetty/run-jetty handler {:port 3000}))
