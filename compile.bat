SET classpath=%CD%\postgresql-42.2.10.jar
javac -cp "%classpath%" -d bin src/*.java -Xlint:unchecked
cd bin
java -cp ".;%classpath%" GUI
cd ..