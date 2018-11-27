replace INTO user(id,name,password) VALUES (1,'大明','$2a$10$0hGefzpJErwu8b2yLbgD.OE3/WNypV15UzD785e5a82hXtqyYZR9q'),(2,'小明','$2a$10$0hGefzpJErwu8b2yLbgD.OE3/WNypV15UzD785e5a82hXtqyYZR9q');
replace INTO role(id,name) VALUES (1,'admin'),(2,'user');
replace INTO user_role(id,user_id,role_id) VALUES (1,1,1),(2,2,2);
replace INTO resource(id,url,http_method) VALUES(100,'/api/login','GET');
replace INTO resource(id,url,http_method) VALUES(200,'/openapi/helloworld','GET');
replace INTO role_resource(id,role_id,resource_id) VALUES(1,1,100),(2,2,200),(3,1,200);
replace INTO expense_history(id,water_count,water_price,elec_count,elec_price,expense_date,create_time,update_time) VALUES (1,84,0.00,3420,0.00,'2017-06-03','2018-11-24 07:43:23','2018-11-24 07:43:23'),(2,86,8.00,3448,1.50,'2017-07-05','2018-11-24 07:44:09','2018-11-24 07:44:09'),(3,88,8.00,3475,1.50,'2017-08-05','2018-11-24 07:44:53','2018-11-24 07:44:53'),(5,90,8.00,3496,1.50,'2017-09-05','2018-11-24 07:46:25','2018-11-24 07:46:25'),(6,92,8.00,3522,1.50,'2017-10-05','2018-11-24 07:47:31','2018-11-24 07:47:31'),(7,95,8.00,3555,1.50,'2017-11-05','2018-11-24 07:47:54','2018-11-24 07:47:54'),(8,96,8.00,3587,1.50,'2017-12-05','2018-11-24 07:48:41','2018-11-24 07:48:41'),(9,98,8.00,3618,1.50,'2018-01-05','2018-11-24 07:49:06','2018-11-24 07:49:06'),(10,99,8.00,3651,1.50,'2018-02-05','2018-11-24 07:49:32','2018-11-24 07:49:32'),(11,100,8.00,3672,1.50,'2018-03-05','2018-11-24 07:50:11','2018-11-24 07:50:11'),(12,102,8.00,3704,1.50,'2018-04-05','2018-11-24 07:51:09','2018-11-24 07:51:09'),(13,104,8.00,3733,1.50,'2018-05-05','2018-11-24 07:51:30','2018-11-24 07:51:30'),(14,105,8.00,3786,1.50,'2018-06-05','2018-11-24 07:55:34','2018-11-24 07:55:34'),(15,107,8.00,3812,1.50,'2018-07-05','2018-11-24 07:55:55','2018-11-24 07:55:55'),(16,109,8.00,3840,1.50,'2018-08-05','2018-11-24 07:56:32','2018-11-24 07:56:32'),(17,111,8.00,3870,1.50,'2018-09-05','2018-11-24 07:57:19','2018-11-24 07:57:19'),(18,112,8.00,3902,1.50,'2018-10-05','2018-11-24 07:57:42','2018-11-24 07:57:42'),(19,113,8.00,3930,1.50,'2018-11-05','2018-11-24 07:58:21','2018-11-24 07:58:21');