(ns todo.core
  (:require [todo.http :as http]))

(defn get-todo-titles
  []
  (let [{:keys [body]} (http/get-todos)]
    (map :title body)))

(defn get-todo-title
  [x]
  (let [{:keys [body]} (http/get-todo x)]
    (:title body)))

(get-todo-titles)