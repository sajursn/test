#1.    Auth service: 
You can use a DB/Cache of your choice. Auth mechanism preferred is OAuth2. Bonus points if you could use a MySQL DB to store tokens rather than the default cache provided by Spring. End result of auth service is, we should be able to call api's to create new users and generate access token using user's username and password. It's good if we can define access control for users. For example: One user could belong to multiple roles and each role can have multiple privileges

#2.    Comments Service: 
Expose minimum of 4 services, were logged in users could CREATE, UPDATE, DELETE and LIST comments. The comments API should include a minimum of id, title and content field. The comments should be able to track who posted it and at what time etc.

#3.    User Service: 
Expose services to CREATE, UPDATE, DELETE and LIST users