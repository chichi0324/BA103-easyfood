# easyfood (2017 iii java project 3th team)

***
##servlet+jsp
***

1.generate sql script to database<br>
(data/sql_script/all.sql)<br>
2.add line tomcat comtext.xml<br>
(ex: <Resource auth="Container" driverClassName="oracle.jdbc.driver.OracleDriver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/TestDB3" password="easyfood" type="javax.sql.DataSource" url="jdbc:oracle:thin:@192.168.99.100:49161:XE" username="easyfood"/> )
3.add jar (about db...)<br>
4.search.jsp<br>
line 23 add<br>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>  <br>
<script type="text/javascript"
  src="https://maps.googleapis.com/maps/api/js?key=[your google map key]" async defer>
</script>  <br>
5.run server<br>
6.http://localhost:8080/easyfood/easyfood/front-end/index.jsp
<br><br>
