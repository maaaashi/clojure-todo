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

(defn find-todo
  [id]
  (-> (jdbc/execute! db
                     ["SELECT * FROM todos WHERE id = ?" (Integer/parseInt id)]
                     {:builder-fn rs/as-unqualified-lower-maps})
      (first)))

(defn insert-todo
  [title completed]
  (-> (jdbc/execute! db
                     ["INSERT INTO todos (title, completed) VALUES (?, ?) RETURNING id, title, completed" title completed]
                     {:builder-fn rs/as-unqualified-lower-maps})
      (first)))

(defn update-todo
  [id title completed]
  (-> (jdbc/execute! db
                     ["UPDATE todos SET title = ?, completed = ? WHERE id = ? RETURNING id, title, completed" title completed (Integer/parseInt id)]
                     {:builder-fn rs/as-unqualified-lower-maps})
      (first)))

(defn delete-todo
  [id]
  (jdbc/execute! db ["DELETE FROM todos WHERE id = ?" (Integer/parseInt id)]))