# launch.sh 
# For git pull project from GitHub and compile project in java

# pull project from GitHub
git pull origin master

# compile project
javac -Djava.ext.dirs=./lib   -encoding utf8  Client.java

# run project
java -Djava.ext.dirs=./lib  Client
