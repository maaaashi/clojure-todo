(ns todo.core
  (:require [todo.http :as http]))

(defn get-todo-titles
  []
  (let [{:keys [body]} (http/get-todos)]
    (map :title body)))

(get-todo-titles)