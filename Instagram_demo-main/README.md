# Instagram_demo

## Framework And language used
- SpringBoot
- Java
## Data Flow
### Model
- 1- User 
- 2- Post

### Controller
- 1- UserController
- 2- PostController

### Service
- 1- UserService
- 2- PostService

### Repository
- 1- UserRepository
- 2- PostRepository

### Database Design
- Here we are using the MYSQL DataBase and it is in a Tabular Form

## Project Summary
In this Project CRUD operations are performed by hitting the API and here are the links for different model:

1) User
- PostMapping -> http://localhost:8080/api/v1/addUser
- GetMapping -> http://localhost:8080/api/v1/getAllUser
- GetMapping -> http://localhost:8080/api/v1/getUser
- PutMapping -> http://localhost:8080/api/v1/putUser/{userId}
- DeleteMapping -> http://localhost:8080/api/v1/deleteUser/{userId}

2) Post
- PostMapping -> http://localhost:8080/api/v1/savePost
- GetMapping - > http://localhost:8080/api/v1/getPost
- PutMapping -> http://localhost:8080/api/v1/updatePost/{postId}
