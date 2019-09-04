# easyfood (2017 iii java project 3th team)

***
##servlet+jsp
***

1.eclipse-->import war<br>
(data/sql_script/easyfood.war)<br>
<br>
2.generate sql script to database<br>
(data/sql_script/all.sql)<br>
<br>
3.add line tomcat comtext.xml<br>
<Resource auth="Container" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/TestDB3" type="javax.sql.DataSource" url="[your jdbc url]" username="easyfood"  password="easyfood" driverClassName="......<br>
<br>
4.add jar (about db...)<br>
<br>
5.WebContent/easyfood/front-end/class/search/search.jsp<br>
line 24 add<br>
<script type="text/javascript"
  src="https://maps.googleapis.com/maps/api/js?key=[your google map key]&callback=initMap" async defer>
</script>  <br>
<br>
6.run server<br>
<br>
7.http://localhost:8080/easyfood/easyfood/front-end/index.jsp
<br><br>
