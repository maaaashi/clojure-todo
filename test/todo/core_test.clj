(ns todo.core-test
  (:require [clojure.test :as t]
            [todo.core :as sut]
            [todo.http :as http]))


(t/deftest get-todo-titles-test
  (t/testing "Todoのタイトルを取得できる"
    (let [todos [{:id 1 :title "朝食を食べる" :completed false}
                 {:id 2 :title "仕事に行く" :completed false}]]
      (with-redefs [http/get-todos (fn [] {:status 200 :body todos})]
        (t/is (= (sut/get-todo-titles) ["朝食を食べる" "仕事に行く"]))))))