(ns todo.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as res]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.route :as route]
            [todo.handler :as handler]))

(defroutes handler'
  (POST "/v1/todos" req handler/post-todos)
  (GET "/v1/todos" [] handler/get-todos)
  (GET "/v1/todos/:id" [id] (handler/get-todo id))
  (PUT "/v1/todos/:id" [id :as req] (handler/put-todo req id))
  (DELETE "/v1/todos/:id" [id] (handler/delete-todo id))
  (route/not-found (res/response {:message "Not Found"})))

(defn- wrap-http-logging
  [handler]
  (fn [req]
    (let [uri (get req :uri)
          method (get req :request-method)
          current-timestamp (-> (java.time.Instant/now)
                                (.toString))]
      (println {:uri uri :method method :current-timestamp current-timestamp}))
    (handler req)))

(def handler
  (-> handler'
      (wrap-json-body {:keywords? true})
      (wrap-http-logging)
      (wrap-json-response)))

(defn -main []
  (jetty/run-jetty handler {:port 3000}))
