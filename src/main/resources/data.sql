replace INTO user(id,name,password) VALUES (1,'大明','$2a$10$0hGefzpJErwu8b2yLbgD.OE3/WNypV15UzD785e5a82hXtqyYZR9q'),(2,'小明','$2a$10$0hGefzpJErwu8b2yLbgD.OE3/WNypV15UzD785e5a82hXtqyYZR9q');
replace INTO role(id,name) VALUES (1,'admin'),(2,'user');
replace INTO user_role(id,user_id,role_id) VALUES (1,1,1),(2,2,2);
replace INTO permission(id,url,http_method) VALUES(100,'/api/login','GET');
replace INTO permission(id,url,http_method) VALUES(200,'/openapi/helloworld','GET');
replace INTO role_permission(role_id,permission_id) VALUES(1,100),(2,200);