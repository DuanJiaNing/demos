select count(1) from sign_in;
select count(1) from sign_in0;
select count(1) from sign_in1;
select count(1) from sign_in2;
select count(1) from sign_in3;
select count(1) from sign_in4;
select count(1) from sign_in5;
select count(1) from sign_in6;


truncate table sign_in;
truncate table sign_in0;
truncate table sign_in1;
truncate table sign_in2;
truncate table sign_in3;
truncate table sign_in4;
truncate table sign_in5;
truncate table sign_in6;

# 新增数据时由路由规则控制插入到哪一张表
# 已经事先在 sign_in0 ~ 3 插入总量为 10 000 条数据，平均每张表越 25000 条数据

# 主维度：customer_id
# zebra 支持的几种查询方式开销分析

# INSERT


