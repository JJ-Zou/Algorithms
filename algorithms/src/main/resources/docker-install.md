```
yum install docker
service docker start
systemctl enable docker
```

```
//mysql
docker pull mysql:5.7.29
mkdir -p /usr/local/mysql/data /usr/local/mysql/logs /usr/local/mysql/conf
vim /usr/local/mysql/conf/my.cnf
docker run -d -p 13306:3306 --name mysql --restart=always -v /usr/local/mysql/conf:/etc/mysql/conf.d -v /usr/local/mysql/logs/:/logs -v /usr/local/mysql/data/:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=admin 5d9483f9a7b2
```