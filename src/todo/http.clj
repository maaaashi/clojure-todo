(ns todo.http)

(defn get-todos
  []
  {:status 200
   :body [{:id 1 :title "朝食を食べる" :completed false}
          {:id 2 :title "昼食を食べる" :completed false}
          {:id 3 :title "夕飯を食べる" :completed false}]})