(ns todo.driver
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]))

(def db-spec
  {:dbtype "postgresql"
   :dbname "todo"
   :host "localhost"
   :port 5432
   :user "todo-user"
   :password "todo-password"})

(def db (jdbc/get-datasource db-spec))

(defn get-todos
  []
  (jdbc/execute! db ["SELECT t.id, t.title, t.completed FROM todos t"]
                 {:builder-fn rs/as-unqualified-lower-maps}))
