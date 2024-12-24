# Todo API

## DB

```bash
$ cd db/
$ docker build . -t todo-db
$ docker run -it --rm -p 5432:5432 todo-db
```