FROM postgreql:latest

ENV POSTGRES_DB=todo
ENV POSTGRES_USER=todo-user
ENV POSTGRES_PASSWORD=todo-password

ADD ./init.sql /docker-entrypoint-initdb.d/