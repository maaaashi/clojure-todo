(ns todo.database
  (require '[next.jdbc :as jdbc]))

(def db-spec
  {:dbtype "postgresql"
   :dbname "todo"
   :host "localhost"
   :port 5432
   :user "todo-user"
   :password "todo-password"})

(def db (jdbc/get-datasource db-spec))

(defn select-todos
  []
  (jdbc/execute! db ["SELECT * FROM todos"]))
