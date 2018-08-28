replace INTO user(id,name,password) VALUES (1,'大明','123456'),(2,'小明','123456');
replace INTO role(id,name) VALUES (1,'admin'),(2,'user');
replace INTO user_role(id,user_id,role_id) VALUES (1,1,1),(2,2,2);