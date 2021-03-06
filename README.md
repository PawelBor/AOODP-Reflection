# AOODP-Reflection
# *Advanced Object Oriented Programming:  Project 2016 - Year 4*

**Name:** Pawel Borzym </br>
**College:** Galway-Mayo Institute of Technology </br>
**Course:** Software Development - Y4 </br>
**Module:** Adv.Object-Oriented Programming </br>
**Lecturer:** Dr.John Healy </br>

###Project
Assignment 2016: Measuring Stability Using the Reflection API File

###Overview
Project's main focus was the usage of Reflection. The ability to dynamically inspect types at run-time.</br>
Java App creates an object graph through inheritence + composition.</br>
Dynamic inspection of object grap can be done through the usage of *Java Reflection API*. **(java.util.reflect)**</br>
It allows the class to be inspected about structure (constructors, methods, returns etc...) of the said class.</br>

That said, this project also gave me a decent try at the *Java Swing* GUI widget toolkit and *Java Abstract Window Toolkit (AWT)*.</br>

####Project Information
**JAR FIle***(Java ARchive)*: User will select JAR file through file explorer which then will be inspected/analysed for all its classes.</br>
The data is obtained and In-Degree *(Affarent Coupling)* and Out-Degree*(Efferent Coupling)* is being calculated of the classes inspected.<br>

*Coupling* | *Description*
---------|----------
**Afferent Couplings (Ca):**| The number of classes in other packages that depend upon classes within the package is an indicator of the package's responsibility. *Afferent = incoming.*
**Efferent Couplings (Ce):**| The number of classes in other packages that the classes in the package depend upon is an indicator of the package's dependence on externalities. *Efferent = outgoing.*

With the use of those 2 calculations, it is possible to calculate the **Stability** for all the classes loaded from the JAR file.</br>
This data is then displayed in a Table in the format of:</br>

| Class Name       | In-Degree      | Out-Degree  | Stability    |
|:----------------:|:--------------:|:-----------:|:------------:|
| ie.gmit.sw.Runner| a              |   b         |      c       |
| ie.gmit.sw.Test  | a              |   b         |      c       |
| ie.gmit.sw.Test2 | a              |   b         |      c       |


### HOW TO RUN
1. Get this Repository
2. ECLIPSE IDE: Create new Java Project (can be any other IDE) - **OR** simply *Import this Java Project (skip next steps this way) *
3. Take the java files and throw into your project.
4. Run the project (make sure to include any External JAR files that your JAR file may include. </br>
*i.e. this project contains .jar file that uses servlet-api.jar which must be addes as external jar in order for the calculator to run.*
5. Select your JAR file and see the result.
