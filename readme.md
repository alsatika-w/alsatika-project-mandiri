This is an online dish ordering program.
It is recommended to test using Postman.
- This program is built using dependencies such as:
    1. Maven
    2. Spring Data JPA
    3. Validation
    4. ORM Hibernate
    5. PostgreSQL driver
    6. Lombok
    7. DevTools
    8. Starter Web
- Native queries are used for some methods like update and delete.
- Stream mapping is used in the billing or transaction section.
- The program uses the clean architecture concept and DTO to facilitate updates.
- There are 4 tables used: menu, customer, bill, and bill detail.
- In this program, you can:
    1. Create a new menu
    2. View all available menus
    3. Update menu name and price
    4. Search for a menu by name
    5. Delete a menu by its ID
    6. Add a new customer
    7. View the list of existing customers
    8. Search for customer data by customer name
    9. Update customer personal data
    10. Update customer membership status
    11. Delete customer data by ID
    12. Make a dish purchase transaction
    13. View the details of the purchase transactions you have made