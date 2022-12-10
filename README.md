## **Event-driven microservice architecture using spring boot and apache kafka**

This is the simple event driven microservice architecture project created using spring boot and apache kafka

**Apache Kafka** is a distributed event store and stream-processing platform.Kafka is an open source software which provides a framework for storing, reading and analysing streaming data.

**Netflix's Zuul Server** is used as **API Gateway** for dynamic routing of requests to different microservices. 

##### **There are 3 services**
  - Post Service
  - Comment Service
  - Query Service

### **Post service** 
This service is solely responsible for creating new post, updating existing post and deleting the post.When new post is created, it emits or produces **"postCreatedEvent"** event to the apache kafka message broker.Query service can consume this event from the apache kafka message broker to store post data in its database.
Similarly, **"postUpdatedEvent"** and **"postDeletedEvent"** are also emitted to the kafka message broker.

###  **Comment service** 
This service is solely responsible for creating new comment, updating existing post and deleting the comment.When new comment is created for a post, it emits **"commentCreatedEvent"** event to the apache kafka message broker.Query service can consume this event from the apache kafka message broker to store comment data in its database.
Similarly, **"commentUpdatedEvent"** and **"commentDeletedEvent"** are also emitted to the kafka message broker.

### **Query service** 
Query service consumes events from both Post service and Comment service.
This service is responsible for storing post data (contains post and comments for that perticular post), fetching all post data and single post data. 
