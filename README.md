# DBS Assignment 5

Author: Dmytro Dzhuha\
[Faculty of Informatics and Information Technologies](https://www.fiit.stuba.sk/)

# Changes from previous assignment
Mostly nothing changed, only added new fields like created_at and updated_at to all tables.\
Also columns where renamed to match the naming convention.
Foreign keys and relations is the same as in previous assignment.

## Authors

## `POST /authors`
### This POST operation allows you to create a new author.

### Body:
The request should contain a JSON object describing the author, including any of the following properties:

- id (optional): A unique identifier for the author represented as a string.
- name: A string representing the first name of the author.
- surname: A string representing the last name of the author.

```json
{
  "id": "7f3c3d0a-8b07-45b9-9d18-4074647d37c8",
  "name": "Ernest",
  "surname": "Hemingway"
}
```
### Responses:

```
201	Created
```
```json
{
  "id": "7f3c3d0a-8b07-45b9-9d18-4074647d37c8",
  "name": "Ernest",
  "surname": "Hemingway",
  "created_at": "2023-04-12T10:00:00Z",
  "updated_at": "2023-04-12T10:00:00Z"
}
```
```
400	Bad Request
```

```
409 Conflict
```

### SQL:
```sql
insert into authors (created_at, name, surname, updated_at, id) values (?, ?, ?, ?, ?)
```


## `GET /authors/{authorId}`
### This GET operation allows you to retrieve the details of a specific author by ID.

### Parameters:
- authorId: Author UUID

### Responses:

```
200	OK
```
```json
{
  "id": "7f3c3d0a-8b07-45b9-9d18-4074647d37c8",
  "name": "Ernest",
  "surname": "Hemingway",
  "created_at": "2023-04-12T10:00:00Z",
  "updated_at": "2023-04-12T10:00:00Z"
}
```
```
404	Not Found
```

### SQL:
```sql
select a1_0.id, a1_0.created_at, a1_0.name, a1_0.surname, a1_0.updated_at from authors a1_0 where a1_0.id = ? fetch first ? rows only
```


## `PATCH /authors/{authorId}`
### This PATCH operation allows you to update the details of a specific author by ID.

### Parameters:
- authorId: Author UUID

### Body:
The request should contain a JSON object describing the updates to be made to the author, including any of the following properties:

- name (optional): A string representing the first name of the author.
- surname (optional): A string representing the last name of the author.

```json
{
  "name": "Ernest",
  "surname": "Hemingway"
}
```
### Responses:
```
200	Ok
```
```json
{
	"name": "Dmytro",
	"surname": "Dzhuha",
	"id": "7f3c3d0a-8b07-45b9-9d18-4074647d37c8",
	"created_at": "2023-05-07T16:05:59.761997+02:00",
	"updated_at": "2023-05-07T16:08:54.8666462+02:00"
}
```
```
404	Not Found
```

### SQL:
```sql
update authors set created_at=?, name=?, surname=?, updated_at=? where id=?
```





## `DELETE /authors/{authorId}`
### This DELETE operation allows you to delete a specific author by ID.

### Parameters:
- authorId: Author UUID

### Responses:
```
204	No Content
```
```
404	Not Found
```

### SQL:
```sql
delete from authors where id=?
```

## Categories

## `POST /categories`
### This POST operation allows you to create a new category.

### Body:
The request should contain a JSON object describing the category, including the following properties:

- id (optional): A unique identifier for the category represented as a string. If not provided, a new UUID will be generated.
- name: A string representing the name of the category.

```json
{
  "id": "6e26d6e7-6c2e-4177-86f1-39274c524399",
  "name": "Thriller"
}
```

### Responses:
```
201	Created
```
```json
{
  "id": "6e26d6e7-6c2e-4177-86f1-39274c524399",
  "name": "Thriller",
  "created_at": "2023-04-12T10:00:00Z",
  "updated_at": "2023-04-12T10:00:00Z"
}
```
```
400	Bad Request
```    
```
409 Conflict
```

### SQL:
```sql
insert into categories (created_at, name, updated_at, id) values (?, ?, ?, ?)
```


## `GET /categories/{categoryId}`
### This GET operation allows you to retrieve the details of a specific category by ID.

### Parameters:
- categoryId: Category UUID

### Responses:
```
200	OK
```
```json
{
  "id": "6e26d6e7-6c2e-4177-86f1-39274c524399",
  "name": "Thriller",
  "created_at": "2023-04-12T10:00:00Z",
  "updated_at": "2023-04-12T10:00:00Z"
}
```
```
404	Not Found
```

### SQL:
```sql
select c1_0.id,c1_0.created_at,c1_0.name,c1_0.updated_at from categories c1_0 where c1_0.id=? fetch first ? rows only
```

## `PATCH /categories/{categoryId}`
### This PATCH operation allows you to update the details of a specific category by ID.

### Parameters:
- categoryId: Category UUID

### Body:
The request should contain a JSON object describing the updates to be made to the category, including the following property:

- name (optional): A string representing the name of the category.

```json
{
  "name": "Thriller"
}
```

### Responses:
```
200	Ok
```
```json
{
  "id": "6e26d6e7-6c2e-4177-86f1-39274c524399",
  "name": "Thriller",
  "created_at": "2023-04-12T10:00:00Z",
  "updated_at": "2023-04-12T10:00:00Z"
}
```
```
400 Bad Request
```    
```
404	Not Found
```
```
409	Conflict
```

### SQL:
```sql
update categories set created_at=?, name=?, updated_at=? where id=?
```

## `DELETE /categories/{categoryId}`
### This DELETE operation allows you to delete a specific category by ID.

### Parameters:
- categoryId: Category UUID

### Responses:
```
204	No Content
```
```
404	Not Found
```

### SQL:
```sql
delete from categories where id=?
```

## Publications

## `POST /publications`
### This POST operation allows you to create a new publication.

### Body:
The request should contain a JSON object describing the publication, including the following properties:

- id (optional): A unique identifier for the publication represented as a string. If not provided, a new UUID will be generated.
- title: A string representing the title of the publication.
- authors: A list of objects representing the authors of the publication. Each author object contains two properties: name (string) and surname (string). The specified author names must match an existing author entity in the database, otherwise the request will fail.
- categories: A list of strings representing the categories that the book belongs to.The specified category names must match an existing category entity in the database, otherwise the request will fail.

```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "authors": [
    {
      "name": "string",
      "surname": "string"
    }
  ],
  "categories": [
    "string"
  ]
}
```

### Responses:

```
201	Created
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "authors": [
    {
      "name": "string",
      "surname": "string"
    }
  ],
  "categories": [
    "string"
  ],
  "created_at": "2023-05-07T14:19:01.460Z",
  "updated_at": "2023-05-07T14:19:01.460Z"
}
```
```
400	Bad Request
```    
### SQL:
```sql
insert into publications (created_at, title, updated_at, id) values (?, ?, ?, ?)
```

## `GET /publications/{publicationId}`
### This GET operation allows you to retrieve the details of a specific publication by ID.

### Parameters:
- publicationId: Publication UUID

### Responses:
```
200	OK
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "authors": [
    {
      "name": "string",
      "surname": "string"
    }
  ],
  "categories": [
    "string"
  ],
  "created_at": "2023-05-07T14:19:01.460Z",
  "updated_at": "2023-05-07T14:19:01.460Z"
}
```
```
404	Not Found
```

### SQL:
```sql
select p1_0.id,p1_0.created_at,p1_0.title,p1_0.updated_at from publications p1_0 where p1_0.id=? fetch first ? rows only
```
With help of ORM authors and categories are fetched as well automatically.

## `PATCH /publications/{publicationId}`
### This PATCH operation allows you to update the details of a specific publication by ID.

### Parameters:
- publicationId: Publication UUID

### Body:
The request should contain a JSON object describing the updates to be made to the publication, including the following properties:

- title (optional): A string representing the title of the publication.
- authors (optional): A list of objects representing the authors of the publication. Each author object contains two properties: name (string) and surname (string). The specified author names must match an existing author entity in the database, otherwise the request will fail.
- categories (optional): A list of strings representing the categories that the book belongs to.The specified category names must match an existing category entity in the database, otherwise the request will fail.

```json
{
  "title": "The Call of Cthulhu",
  "authors": [
    {
      "name": "H.P.",
      "surname": "Lovecraft"
    }
  ],
  "categories": [
    "Horror",
    "Fiction"
  ]
}
```

### Responses:
```
200	Ok
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "The Call of Cthulhu",
  "authors": [
    {
      "name": "H.P.",
      "surname": "Lovecraft"
    }
  ],
  "categories": [
    "Horror",
    "Fiction"
  ],
  "created_at": "2023-05-07T14:19:01.460Z",
  "updated_at": "2023-05-07T14:19:01.460Z"
}
```
```
400	Bad Request
```    
```
404	Not Found
```

### SQL:
```sql
delete from publication_authors where publication_id=?
delete from publication_categories where publication_id=?
insert into publication_authors (author_id, publication_id) values (?, ?)
insert into publication_categories (category_id, publication_id) values (?, ?)
update publications set created_at=?, title=?, updated_at=? where id=?
```
With help of ORM all authors and categories are deleted and inserted again.

## `DELETE /publications/{publicationId}`
### This DELETE operation allows you to delete a specific publication by ID.

### Parameters:
- publicationId: Publication UUID

### Responses:
```
204	No Content
```
```
404	Not Found
```

### SQL:
```sql
delete from publications where id=?
```

## Publication instances

## `POST /instances`
### This POST operation allows you to create a new publication instance.

### Body:

The request should contain a JSON object describing the publication instance, including the following properties:

- id (optional): A unique identifier for the publication instance represented as a string. If not provided, a new UUID will be generated.
type: A string representing the type of the publication instance. Valid values include "physical", "ebook", and "audiobook".
- publisher: A string representing the name of the publisher of the publication instance.
- year: An integer representing the year of publication of the publication instance.
status (optional): A string representing the current status of the publication instance. Valid values include "available" and "reserved". If not provided, the default status is "available".
- publication_id: A string representing the UUID of the publication that the instance belongs to.

```json
{
  "id": "07d6ee43-d6d2-46b8-8b44-1d3a813a98ee",
  "type": "physical",
  "publisher": "Penguin Books",
  "year": 1949,
  "status": "available",
  "publication_id": "d1ca6e2a-9d7b-437d-84e7-986f0bb931f7"
}
```

### Responses:
```
201	Created
```
```json
{
  "id": "07d6ee43-d6d2-46b8-8b44-1d3a813a98ee",
  "type": "physical",
  "publisher": "Penguin Books",
  "year": 1949,
  "status": "available",
  "publication_id": "d1ca6e2a-9d7b-437d-84e7-986f0bb931f7",
  "created_at": "2023-05-07T14:30:56.917Z",
  "updated_at": "2023-05-07T14:30:56.917Z"
}
```
```
400	Bad Request
```

### SQL:
```sql
insert into publication_instances (created_at, publication_id, publisher, status, type, updated_at, year, id) values (?, ?, ?, ?, ?, ?, ?, ?)
```

## `GET /instances/{instanceId}`
### This GET operation allows you to retrieve the details of a specific publication instance by ID.

### Parameters:
- instanceId: Publication instance UUID

### Responses:
```
200	Ok
```
```json
{
  "id": "07d6ee43-d6d2-46b8-8b44-1d3a813a98ee",
  "type": "physical",
  "publisher": "Penguin Books",
  "year": 1949,
  "status": "available",
  "publication_id": "d1ca6e2a-9d7b-437d-84e7-986f0bb931f7",
  "created_at": "2023-05-07T14:30:56.917Z",
  "updated_at": "2023-05-07T14:30:56.917Z"
}
```
```
404	Not Found
```

### SQL:
```sql
select p1_0.id,p1_0.created_at,p1_0.title,p1_0.updated_at from publications p1_0 where p1_0.id=?
```

## `PATCH /instances/{instanceId}`
### This PATCH operation allows you to update the details of a single publication instance by its ID.

### Parameters:
- instanceId: Publication instance UUID

### Body:

The request should contain a JSON object describing the changes to be made to the publication instance, including any of the following properties:

- type: A string representing the type of the publication instance. Valid values include "physical", "ebook", and "audiobook".
- publisher: A string representing the name of the publisher of the publication instance.
- year: An integer representing the year of publication of the publication instance.
- status: A string representing the current status of the publication instance. Valid values include "available" and "reserved".

```json
{
  "type": "physical",
  "publisher": "Penguin Books",
  "year": 1949,
  "status": "available"
}
```

### Responses:
```
200	Ok
```
```json
{
  "id": "07d6ee43-d6d2-46b8-8b44-1d3a813a98ee",
  "type": "physical",
  "publisher": "Penguin Books",
  "year": 1949,
  "status": "available",
  "publication_id": "d1ca6e2a-9d7b-437d-84e7-986f0bb931f7",
  "created_at": "2023-05-07T14:30:56.917Z",
  "updated_at": "2023-05-07T14:30:56.917Z"
}
```
```
400 Bad Request
```
```
404	Not Found
```

### SQL:
```sql
update publication_instances set created_at=?, publication_id=?, publisher=?, status=?, type=?, updated_at=?, year=? where id=?
```

## `DELETE /instances/{instanceId}`
### This DELETE operation allows you to delete a single publication instance by its ID.

### Parameters:
- instanceId: Publication instance UUID

### Responses:
```
204	No Content
```
```
404	Not Found
```

### SQL:
```sql
delete from publication_instances where id=?
```

## Users

## `POST /users`
### This POST operation allows you to create a new user.

### Body:

The request should contain a JSON object describing the user to be created, including any of the following properties:

- name: A string representing the name of the user.
- surname: A string representing the surname of the user.
- email: A string representing the email address of the user.
- birth_date: A string representing the birth date of the user.
- personal_identificator: A string representing the personal identificator of the user.

```json
{
  "id": "497f6eca-6276-4993-bfeb-53cbbbba6f08",
  "name": "string",
  "surname": "string",
  "email": "user@example.com",
  "birth_date": "2019-08-24",
  "personal_identificator": "string"
}
```

### Responses:
```
201	Created
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "surname": "string",
  "email": "user@example.com",
  "birth_date": "2023-05-07",
  "personal_identificator": "string",
  "reservations": [
    {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "publication_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "created_at": "2023-05-07T14:38:54.045Z"
    }
  ],
  "rentals": [
    {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "publication_instance_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "duration": 0,
      "start_date": "2023-05-07T14:38:54.045Z",
      "end_date": "2023-05-07T14:38:54.045Z",
      "status": "active"
    }
  ],
  "created_at": "2023-05-07T14:38:54.045Z",
  "updated_at": "2023-05-07T14:38:54.045Z"
}
```
```
400 Bad Request
```
```
409	Conflit
```

### SQL:
```sql
insert into users (id, name, surname, email, birth_date, personal_identificator, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?, ?)
```

## `GET /users/{userId}`
### This GET operation allows you to retrieve the details of a specific user by ID.

### Parameters:
- userId: User UUID

### Responses:
```
200	Ok
```
```json
{
  "id": "497f6eca-6276-4993-bfeb-53cbbbba6f08",
  "name": "string",
  "surname": "string",
  "email": "user@example.com",
  "birth_date": "2019-08-24",
  "personal_identificator": "string",
  "reservations": [
    {
      "id": "497f6eca-6276-4993-bfeb-53cbbbba6f08",
      "user_id": "a169451c-8525-4352-b8ca-070dd449a1a5",
      "publication_id": "c3452ed9-df18-461f-a6fe-5036c6b93bf2",
      "created_at": "2019-08-24T14:15:22Z"
    }
  ],
  "rentals": [
    {
      "id": "497f6eca-6276-4993-bfeb-53cbbbba6f08",
      "user_id": "a169451c-8525-4352-b8ca-070dd449a1a5",
      "publication_instance_id": "d53415a1-1c04-429f-a852-288aba36578a",
      "duration": 0,
      "start_date": "2019-08-24T14:15:22Z",
      "end_date": "2019-08-24T14:15:22Z",
      "status": "active"
    }
  ],
  "created_at": "2019-08-24T14:15:22Z",
  "updated_at": "2019-08-24T14:15:22Z"
}
```
```
404	Not Found
```

### SQL:
```sql
select r1_0.user_id,r1_0.id,r1_0.created_at,r1_0.publication_id from reservations r1_0 where r1_0.user_id=?
```

## `PATCH /users/{userId}`
### This PATCH operation allows you to update the details of a single user by its ID.

### Parameters:
- userId: User UUID

### Body:

The request should contain a JSON object describing the changes to be made to the user, including any of the following properties:

- name: A string representing the name of the user.
- surname: A string representing the surname of the user.
- email: A string representing the email address of the user.
- birth_date: A string representing the birth date of the user.
- personal_identificator: A string representing the personal identificator of the user.

```json
{
  "name": "string",
  "surname": "string",
  "email": "user@example.com",
  "birth_date": "2019-08-24",
  "personal_identificator": "string"
}
```

### Responses:
```
200	Ok
```
```json
{
	"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
	"name": "string",
	"surname": "string",
	"email": "user@gmail.com",
	"birth_date": "2004-05-31",
	"created_at": "2023-05-05T17:00:57.124+02:00",
	"updated_at": "2023-05-07T16:42:49.1584163+02:00",
	"personal_identificator": "string"
}
```
```
400 Bad Request
```
```
404	Not Found
```

### SQL:
```sql
update users set birth_date=?, created_at=?, email=?, name=?, personal_id=?, surname=?, updated_at=? where id=?
```

## Cards

## `POST /cards`
### This POST operation allows you to create a new RFID card for a user.

### Body:

The request should contain a JSON object describing the RFID card as follows:

- id (optional): A unique identifier for the RFID card represented as a string. If not provided, a new UUID will be generated.
- user_id: A unique identifier for the user associated with the RFID card represented as a string.
- magstripe: A string representing the magstripe data stored on the RFID card.
- status: A string representing the status of the RFID card. The value can be one of "active", "inactive", or "expired".

```json
{
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "magstripe": "string",
  "status": "active"
}
```

### Responses:
```
201	Created
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "magstripe": "string",
  "status": "active",
  "created_at": "2023-05-05T17:00:57.124+02:00",
  "updated_at": "2023-05-07T16:42:49.1584163+02:00"
}
```
```
400 Bad Request
```

### SQL:
```sql
insert into user_cards (created_at, magstripe, status, updated_at, user_id, id) values (?, ?, ?, ?, ?, ?)
```

## `GET /cards/{cardId}`
### This GET operation allows you to retrieve details about a specific RFID card by ID.

### Parameters:
- cardId: RFID card UUID

### Responses:
```
200	Ok
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "magstripe": "string",
  "status": "active",
  "created_at": "2023-05-05T17:00:57.124+02:00",
  "updated_at": "2023-05-07T16:42:49.1584163+02:00"
}
```
```
404	Not Found
```

### SQL:
```sql
select c1_0.id,c1_0.created_at,c1_0.magstripe,c1_0.status,c1_0.updated_at,c1_0.user_id from user_cards c1_0 where c1_0.id=? fetch first ? rows only
```

### `PATCH /cards/{cardId}`
### This PATCH operation allows you to update the status and user owner of an RFID card.

### Parameters:
- cardId: RFID card UUID

### Body:

The request should contain a JSON object describing the changes to be made to the RFID card, including any of the following properties:

- status: A string representing the new status of the RFID card. The value can be one of "active", "inactive", or "expired".
- user_id: A unique identifier for the user associated with the RFID card represented as a string.

```json
{
  "status": "active",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

### Responses:
```
200	Ok
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "magstripe": "string",
  "status": "active",
  "created_at": "2023-05-05T17:00:57.124+02:00",
  "updated_at": "2023-05-07T16:42:49.1584163+02:00"
}
```
```
400 Bad Request
```
```
404	Not Found
```

### SQL:
```sql
update user_cards set created_at=?, magstripe=?, status=?, updated_at=?, user_id=? where id=?
```

## `DELETE /cards/{cardId}`
### This DELETE operation allows you to delete a specific RFID card by ID.

### Parameters:
- cardId: RFID card UUID

### Responses:
```
204	No Content
```
```
404	Not Found
```

### SQL:
```sql
delete from user_cards where id=?
```

## Reservations

## `POST /reservations`
### This POST operation allows a user to create a new reservation for a publication that is currently unavailable.

### Body:

The request should contain a JSON object describing the new reservation, including the following properties:

- user_id: A string representing the UUID of the user making the reservation.
- publication_id: A string representing the UUID of the publication being reserved.
Optionally, the request can also include an id property representing a UUID for the reservation, if a specific ID is desired.

```json
{
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

### Responses:
```
201	Created
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "created_at": "2023-05-05T17:00:57.124+02:00"
}
```
```
400 Bad Request
```

### SQL:
```sql
insert into reservations (created_at, publication_id, user_id, id) values (?, ?, ?, ?)
```

## `GET /reservations/{reservationId}`
### This GET operation allows you to retrieve details about a specific reservation by ID.

### Parameters:
- reservationId: Reservation UUID

### Responses:
```
200	Ok
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "created_at": "2023-05-05T17:00:57.124+02:00"
}
```
```
404	Not Found
```

### SQL:
```sql
select r1_0.id,r1_0.created_at,r1_0.publication_id,r1_0.user_id from reservations r1_0 where r1_0.id=? fetch first ? rows only
```

## `DELETE /reservations/{reservationId}`
### This DELETE operation allows you to delete a specific reservation by ID.

### Parameters:
- reservationId: Reservation UUID

### Responses:
```
204	No Content
```
```
404	Not Found
```

### SQL:
```sql
delete from reservations where id=?
```

## Rentals

## `POST /rentals`
### This POST operation allows a user to create a new rental for a publication.

### Body:

The request should contain a JSON object describing the rental, including the following properties:

- user_id: A string representing the UUID of the user creating the rental.
- publication_id: A string representing the UUID of the publication being rented.
- duration: An integer representing the duration of the rental in days. The maximum duration is 14 days.

```json
{
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "duration": 14,
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

### Responses:
```
201	Created
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "duration": 14,
  "start_date": "2023-05-05T17:00:57.124+02:00",
  "end_date": "2023-05-19T17:00:57.124+02:00"
}
```
```
400 Bad Request
```

### SQL:
```sql
insert into rentals (duration, end_date, publication_instance_id, start_date, status, user_id, id) values (?, ?, ?, ?, ?, ?, ?)
```

## `GET /rentals/{rentalId}`
### This GET operation allows you to retrieve details about a specific rental by ID.

### Parameters:
- rentalId: Rental UUID

### Responses:
```
200	Ok
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_instance_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "duration": 0,
  "start_date": "2023-05-05T17:00:57.124+02:00",
  "end_date": "2023-05-05T17:00:57.124+02:00"
}
```
```
404	Not Found
```

### SQL:
```sql
select r1_0.id,r1_0.duration,r1_0.end_date,r1_0.publication_instance_id,r1_0.start_date,r1_0.status,r1_0.user_id from rentals r1_0 where r1_0.id=? fetch first ? rows only
```

## `PATCH /rentals/{rentalId}`
### This PATCH operation allows a user to extend the reservation time for a rental.

### Body:

The request should contain a JSON object describing the changes to be made to the rental, including the following property:

- duration: An integer representing the new duration of the rental in days. The maximum duration is 14 days.

```json
{
  "duration": 14,
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

### Responses:
```
200	Ok
```
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "user_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "publication_instance_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "duration": 14,
  "start_date": "2023-05-05T17:00:57.124+02:00",
  "end_date": "2023-05-19T17:00:57.124+02:00"
}
```
```
400 Bad Request
```
```
404	Not Found
```

### SQL:
```sql
update publication_instances set created_at=?, publication_id=?, publisher=?, status=?, type=?, updated_at=?, year=? where id=?
```
