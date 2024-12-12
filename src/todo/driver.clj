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
  (jdbc/execute! db ["SELECT * FROM todos"]
                 {:builder-fn rs/as-unqualified-lower-maps}))

(defn get-todo
  [id]
  (-> (jdbc/execute! db ["SELECT * FROM todos WHERE id = ?" (Integer/parseInt id)]
                     {:builder-fn rs/as-unqualified-lower-maps})
      (first)))
