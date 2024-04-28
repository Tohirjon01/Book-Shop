
>>HEAD
# Book Shop -service

### Automation of Book Shop  operation systems

This system includes:

* Reformed a User details
* Chance to perform actions on Book
* Chance to perform actions on card
* Using Spring security to authenticate users
* the ability to save orders to the Cart
* granting and restricting permissions and roles to Users by super admin
* Get user statistics of all orders books
* View all orders

```java

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableWebSecurity
public class BookShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }
 /* SecurityContextHolder.getContext().getAuthentication().getName();
            principal.getName();*/
}

```

***You can get the services listed above in this table through the links***

| No |                                                                               Services                                                                               | Status |
|:--:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:------:|
| 1  |            [User Service](https://github.com/Tohirjon01/Book-Shop/blob/master/src/main/java/uz/bookshop/controller/UserController.java)             |   ✅    |
| 2  |            [Book Service](https://github.com/Tohirjon01/Book-Shop/blob/master/src/main/java/uz/bookshop/controller/BookController.java)             |   ✅    |
| 3  |         [Comment Service](https://github.com/Tohirjon01/Book-Shop/blob/master/src/main/java/uz/bookshop/service/CommentService.java)          |   ✅    |
| 4  |          [Role Service ](https://github.com/Tohirjon01/Book-Shop/blob/master/src/main/java/uz/bookshop/controller/RoleController.java)          |   ✅    |
| 5  |        [Order Service]()         |   ♻️    |
| 6  |   [Statistics Service]()    |   ♻️    |

>>>>origin/master

```
