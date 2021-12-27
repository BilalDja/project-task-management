# Project Task Management

### Installation

- Config the **mail smtp** provider by using your **google** account or create an **Mailtrap** account for testing
- Config the **app.storage.location** to the project directory, the storage directory is located in **./sotrage**
- Default Profile is using **H2 database** for testing you can switch to **PostgresSql** for production using the
  profile : **psql***
- password to all accounts is : 123
- After creating an account by the admin an email send to the user contain the current password

### Bootstrap

- ./mvnw spring-boot:run
- yarn install | npm install
- ng serve

### Features

- Multiuser: Admin & User
- Crud User
- Crud Task
- User can access to his tasks
- User can update task status
- User can update its account information
- Dashboard overview for Admin
- Password reset via email notification
- Register a new account

### Upcoming Features

- Manage tasks by projects
- Improve alert notification
- Improve reactivity & UI experience
- Refactor and documentation
- Integrate liquibase for versioning the database
