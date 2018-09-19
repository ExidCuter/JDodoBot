# JDodoBot-2.0

New version of JDodoBot. [Add it to your server](https://discordapp.com/oauth2/authorize?client_id=277458741052571648&scope=bot&permissions=2146958591)

### Prerequisites

What things you will need to run the software

```
Java 8, MySql Server, Gradle
```

### Usage

#### In Linux
You need to install the following dependencies:
`Java 8`, `MySql Server`, `zip`, `unzip` and `gradle`  

#####In Ubuntu or other "Debian flavors" you can manually install them with:
```
#install java
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer

#install mysql
sudo apt install mysql-server

#install zip and unzip
sudo apt install unzip zip

#install sdkman
curl -s "https://get.sdkman.io" | bash
source "/home/$USER/.sdkman/bin/sdkman-init.sh"

#install gradle
sdk install gradle
```

than run:
```
#build the project
gradle build -x test

#run the bot
gradle bootRun
```

#####or download `install.sh` and run it

#### In Windows

Paste your bot token into and api keys in ```settings.txt```.

To run the bot open CMD in the root of the project. Than type: 
```
gradlew build -x test

gradlew bootRun
``` 

#### Commands

#####Bot usage:
```
prefix + command
```

#####Example:
```
!cat
```

For the list of all commands type ```!help```

