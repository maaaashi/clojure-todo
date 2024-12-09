(ns todo.core
  (:require [todo.http :as http]
            [ring.adapter.jetty :as jetty]))

(defn get-todo-titles
  []
  (let [{:keys [body]} (http/get-todos)]
    (map :title body)))

(defn get-todo-title
  [x]
  (let [{:keys [body]} (http/get-todo x)]
    (:title body)))

(get-todo-titles)

(defn handler
  [request]
  (println request)
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, World!"})

(defn -main []
  (println "アプリケーションを起動します")
  (jetty/run-jetty handler {:port 3000}))

(defn logging-middleware [handler]
  (fn [request]
    (println "Request received:" request)
    (handler request)))
