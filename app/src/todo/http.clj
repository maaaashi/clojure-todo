(ns todo.http)

(defn get-todos
  []
  {:status 200
   :body [{:id 1 :title "1111111" :completed false}
          {:id 2 :title "2222222" :completed false}
          {:id 3 :title "3333333" :completed false}]})

(defn get-todo
  [x]
  {:status 200
   :body [{:id x :title "1111111" :completed false}]})