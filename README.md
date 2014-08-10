%Review your code often is a good habit%

zhengli:

Java files,you just zip these files to jar file and add this jar to the hadoop classpath

githelp:

some operation of github

data:

you can download the data from here ---

    http://tejp.de/files/so/dbdump/stackexchange-export-2011-09-01/

javac HelloWorld.java

javap -classpath . -c HelloWorld

javap .............-verbose HelloWorld

JVM load ---> link ---> initialize the class

java is capable of loading classes at run time

java -verbose:class -classpath . compiler . TestLoader

A class is loaded only when it is used:
    
    when the new bytecode is executed, for example, someclass f = new someclass(),

    when the bytecodes make a static reference to a class

