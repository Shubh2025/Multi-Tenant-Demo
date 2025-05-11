After clonning the code follow below steps to run it :

Run below compose command in terminal:
1> docker-compose up --build 

2> Hit signup APi from Postman :
url : POST => http://localhost:8080/api/signup
Body 
{
    "name": "Pravin",
    "email": "pravinva@gmail.com",
    "password": "1234",
    "webAddress": "pravin23"
}

3> Add Project : 
Header : X-Tenant-ID = tenantId
url : POST => http://localhost:8080/api/project
Body
{
   "name": "Project-2",
   "description": "B2B Platform Creation"
}

After adding some Tenants and project in respective tenants we can check tables using below commands;
run below command to  intercat with DB:
 1:  docker exec -it mysql-db mysql -u root -p root 
 2: SHOW DATABASES;
 3: use master_db; (for chechiong signup table)
  or
 4: use tenant_abc db for checking project tables of respective tenants.


