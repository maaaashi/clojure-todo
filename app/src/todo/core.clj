(ns todo.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as res]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.route :as route]
            [todo.handler :as handler]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [ring.logger :as logger]))

(defroutes handler'
  (POST "/v1/todos" req (handler/post-todos req))
  (GET "/v1/todos" [] handler/get-todos)
  (GET "/v1/todos/:id" [id] (handler/get-todo id))
  (PUT "/v1/todos/:id" [id :as req] (handler/put-todo req id))
  (DELETE "/v1/todos/:id" [id] (handler/delete-todo id))
  (route/not-found (res/response {:message "Not Found"})))

(defn- wrap-log-request
  [handler]
  (fn [req]
    (let [current-timestamp (-> (java.time.Instant/now)
                                (.toString))]
      (log/info (json/write-str {:type "request"
                                 :timestamp current-timestamp
                                 :request_uri (:uri req)
                                 :request_method (:request-method req)})))
    (handler req)))

(defn- wrap-log-response
  [handler]
  (fn [req]
    (let [start-time (System/nanoTime)
          response (handler req)
          end-time (System/nanoTime)
          response-time (/ (- end-time start-time) 1e6)
          current-timestamp (-> (java.time.Instant/now)
                                (.toString))]
      (log/info (json/write-str {:type "response"
                                 :timestamp current-timestamp
                                 :status (:status response)
                                 :request_uri (:uri req)
                                 :request_method (:request-method req)
                                 :response_time response-time}))
      response)))

(defn wrap-error-handling
  [handler]
  (fn [req]))

(def handler
  (-> handler'
      (wrap-log-response)
      (wrap-json-response)
      (wrap-json-body {:keywords? true})
      (wrap-log-request)))

(defn -main []
  (jetty/run-jetty handler {:port 3000}))
