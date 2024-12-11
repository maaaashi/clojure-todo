(ns todo.core
  (:require [todo.http :as http]
            [ring.adapter.jetty :as jetty]
            [next.jdbc :as jdbc]))

(defn get-todos
  []
  (let [{:keys [body]} (http/get-todos)]
    (map :title body)))


(defn handler
  [{:keys [uri request-method]}]
  (condp = uri
    "/todos" (condp = request-method
               :get (get-todos))))

(defn -main []
  (println "アプリケーションを起動します")
  (jetty/run-jetty handler {:port 3000}))

(defn logging-middleware [handler]
  (fn [request]
    (println "Request received:" request)
    (handler request)))


(def db-spec
  {:dbtype "postgresql"
   :dbname "todo"
   :host "127.0.0.1"
   :port 5432
   :user "todo-user"
   :password "todo-password"})

(def db (jdbc/get-datasource db-spec))

(defn select-todos
  []
  (jdbc/execute! db ["SELECT * FROM todos"]))

(select-todos)