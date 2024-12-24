(ns todo.core-test
  (:require [clojure.test :as t]
            [todo.core :as sut]
            [todo.http :as http]
            [mockfn.macros :as mockfn]
            [mockfn.matchers :as matchers]))


(t/deftest get-todo-titles-test2
  (t/testing "特定のTodoのタイトルを取得でき、http/get-todoが呼ばれている"
    (mockfn/verifying
     [(http/get-todo 1) {:status 200 :body {:id 1 :title "朝食を食べる" :completed false}} (matchers/exactly 2)
      (http/get-todo 2) {:status 200 :body {:id 1 :title "仕事に行く" :complated true}} (matchers/exactly 1)]
     (t/is (= (sut/get-todo-title 1) "朝食を食べる"))
     (t/is (= (sut/get-todo-title 2) "仕事に行く")))))

