# _Igrzyska Ścierki 2.0_
The second edition of the Igrzyska Ścierki game. This time, I'm focusing on a code quality too, not only on its workability ;)<br/>
Also, I'm implementing a <b>Spring Security 5</b> to secure access to the app.<br/>

It's a simple browser game where you can compete with your housemates, who are a bigger clean-freak.<br/>You add yourself and your mates as players, create quests in house chores style (with specific point values). When it's done, you can start playing.<br/>First, you must specify when the concrete game will end. Then, the tournament has been begun.<br/>When someone of you completes any quest, the one is logging in the app and ticking who and what done it.<br/>The winner will be that one who collects the most points, in specified time before.
<br/>

The project contains the HSQL database, in case that I want to run it on computers without MySQL installed.<br/>
If you want to change a database to the HQSL, just <b>comment</b> lines about the MySQL and <b>uncomment</b> the HQSL ones, in <b>application.properties</b> and <b>pom.xml</b>
<br/><br/>

**BUILD'n'RUN project:**

- For Unix:

1) Open a project's main folder in a terminal.
2) Use a command: `./mvnw spring-boot:run`.

- For Windows:

1) Open a project's main folder in a console.
2) Use a command: `.\mvnw.cmd spring-boot:run`.
<br><br>
