
# Contax Proyect

Project corresponding to CONTAX technical test
###

## API Reference

Default application port = 8080
```http
  localhost:8080
```
Access to the endpionts documentation with Swagger-UI
```http
  localhost:8080/swagger-ui.html
```
###

## Documentation

###

### Message Entity

| Parameter      | Type            | Description                       |
|:---------------|:----------------|:----------------------------------|
| `id`           | `Long`          | Message ID (Autoincremental)      |
| `title`        | `String`        | **Required**. Message Title       |
| `content`      | `String`        | **Required**. Message content     |
| `creationDate` | `LocalDateTime` | Date and time of message creation |
| `updateDate`   | `LocalDateTime` | Date and time of message update   |

###
###

### Endpoints

| Verbs    | URL Path         | Description                                          |
|:---------|:-----------------|:-----------------------------------------------------|
| `GET`    | `/messages`      | Retrieves any existing messages                      |
| `POST`   | `/messages`      | Creates a new message                                |
| `GET`    | `/messages/{id}` | Retrieve a particular message by ID                  |
| `PUT`    | `/messages/{id}`      | Update the content of a particular message by its ID |
| `DELETE` | `/messages/{id}`      | Deletes a message of the given ID                    |

###

#### REQUEST
> GET | localhost:8080/messages
#### RESPONSE
```json
[
  {
    "id": 1,
    "title": "Title 1",
    "content": "Content 1",
    "creationDate": "2023-09-20T19:57:39.304901",
    "updateDate": "2023-09-20T19:58:12.169894"
  },
  {
    "id": 2,
    "title": "Title N",
    "content": "Content N",
    "creationDate": "2023-09-20T19:57:54.11635",
    "updateDate": "2023-09-20T19:57:54.11635"
  }
]
```

----

----
#### REQUEST
> POST | localhost:8080/messages
#### Body
```json
{
  "title": "Title 1",
  "content": "Content 1"
}
```
#### RESPONSE
```json
{
  "id": 1,
  "title": "Title 1",
  "content": "Content 1",
  "creationDate": "2023-09-20T19:57:39.304901",
  "updateDate": "2023-09-20T19:58:12.169894"
}
```

----

----

#### REQUEST
> GET | localhost:8080/messages/1
#### RESPONSE
```json
{
  "id": 1,
  "title": "Title 1",
  "content": "Content 1",
  "creationDate": "2023-09-20T19:57:39.304901",
  "updateDate": "2023-09-20T19:58:12.169894"
}
```
----

----

#### REQUEST
> PUT | localhost:8080/messages/1
#### Body
```json
{
  "title": "Title Updated",
  "content": "Content Updated"
}
```
#### RESPONSE
```json
{
  "id": 1,
  "title": "Title Updated",
  "content": "Content Updated",
  "creationDate": "2023-09-20T19:57:39.304901",
  "updateDate": "2023-09-20T19:58:12.169894" (New UpdateDate)
}
```

----

----

#### REQUEST
> DELETE | localhost:8080/messages/1
#### RESPONSE
```json
{
  "id": 1,
  "title": "Title Updated",
  "content": "Content Updated",
  "creationDate": "2023-09-20T19:57:39.304901",
  "updateDate": "2023-09-20T19:58:12.169894" 
}
```


###

### Errors
In case an error occurs, it will be displayed as follows in JSON

```json
{
  "message" : "Particular Error"
}
```

