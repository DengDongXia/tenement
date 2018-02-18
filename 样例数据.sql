insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000001","test1@qq.com","test1","test1","null",false,"13800138000");
insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000002","test2@qq.com","test2","test2","null",false,"13800138000");
insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000003","test3@qq.com","test3","test3","null",false,"13800138000");
insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000004","test4@qq.com","test4","test4","null",true,"13800138000");
insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000005","test5@qq.com","test5","test5","null",true,"13800138000");
insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000006","test6@qq.com","test6","test6","null",true,"13800138000");
insert into user(id, email, userName, psw, head, isLandlord, phone) values("00000007","test7@qq.com","test7","test7","null",true,"13800138000");


insert into admin(id, userName, psw) values("00000001","admin1","admin1");

insert into house(title, province, city, region, price, address, comment, publisher, count) values("三房一厅","广东","广州","天河",4000,"龙洞北","balabala","00000004",2);
insert into house(title, province, city, region, price, address, comment, publisher, count) values("三房二厅","广东","广州","天河",4000,"龙洞北","balabala","00000005",2);
insert into house(title, province, city, region, price, address, comment, publisher, count) values("三房三厅","广东","广州","天河",4000,"龙洞北","balabala","00000006",2);
insert into house(title, province, city, region, price, address, comment, publisher, count) values("二房一厅","广东","广州","天河",4000,"龙洞北","balabala","00000007",2);
insert into house(title, province, city, region, price, address, comment, publisher, count) values("三一一厅","广东","广州","天河",4000,"龙洞北","balabala","00000004",2);


insert into notice(userTo, comment) values("00000001","你是一");
insert into notice(userTo, comment) values("00000002","你是二");
insert into notice(userTo, comment) values("00000003","你是三");
insert into notice(userTo, comment) values("00000004","你是四");
insert into notice(userTo, comment) values("00000005","你是五");
insert into notice(userTo, comment) values("00000006","你是六");
insert into notice(userTo, comment) values("00000007","你是七");

insert into orders(userId, houseId, confirm) values("0000001",1,0);
insert into orders(userId, houseId, confirm) values("0000002",2,2);
insert into orders(userId, houseId, confirm) values("0000003",3,0);
insert into orders(userId, houseId, confirm) values("0000001",4,1);
insert into orders(userId, houseId, confirm) values("0000002",5,-1);
insert into orders(userId, houseId, confirm) values("0000003",1,2);
insert into orders(userId, houseId, confirm) values("0000004",2,1);
insert into orders(userId, houseId, confirm) values("0000002",3,2);


insert into picture(houseId,picPath) values(1,"1.jpg");
insert into picture(houseId,picPath) values(2,"1.jpg");
insert into picture(houseId,picPath) values(3,"1.jpg");
insert into picture(houseId,picPath) values(4,"1.jpg");
insert into picture(houseId,picPath) values(5,"1.jpg");
insert into picture(houseId,picPath) values(1,"1.jpg");
insert into picture(houseId,picPath) values(2,"1.jpg");
insert into picture(houseId,picPath) values(3,"1.jpg");