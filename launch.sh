# launch.sh 
# For git pull project from GitHub and compile project in java

# pull project from GitHub
git pull origin master

# compile project
javac -d ./bin/jxner -Djava.ext.dirs=./lib  ./src/jxner/Client.java

# run project
java -cp ./bin/jxner -Djava.ext.dirs=./lib  jxner.Client
