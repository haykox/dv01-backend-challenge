# DV01 backend challenge

## Running the app

Run this using [sbt](http://www.scala-sbt.org/).
Use at least `4G` of heap to avoid OutOfMemoryErrors.

```bash
sbt run -J-Xms4G
```

And then go to <http://localhost:9000> to see the running web application.

## Controllers

- `HomeController.scala`:

  Shows how to load a csv file


## Submit Query

Run below `curl` command to `POST` `JSON` queries. It supports retrieval by fields, filtering for
numeric fields, aggregate functions for numeric fields as well as grouping.
As further enhancements we can also add filtering on aggregate result, sorting and limit.
The field names match those in the `CSV` file.

```bash
curl --location 'http://localhost:9000/insights' \
--header 'Content-Type: text/plain' \
--data '{
"fields": [
{"name": "emp_title"},
{"name": "addr_state"},
{"name": "total_pymnt"},
{"name": "loan_amnt", "aggregate": "Sum"},
{"name": "loan_amnt", "aggregate": "Avg"},
{"name": "loan_amnt", "aggregate": "Count"},
{"name": "loan_amnt", "aggregate": "Min"},
{"name": "loan_amnt", "aggregate": "Max"}
],
"conditions": [
{"field": "total_pymnt", "operator": ">", "value": 2000},
{"field": "total_pymnt", "operator": "<=", "value": 5600}
],
"groupBy": ["emp_title"]
}'
```
