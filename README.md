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

#####Download `install.sh` and run it with `./install.sh` (make sure you made it executable)

#####Or in Ubuntu or other "Debian flavors" you can manually install them with:
```bash
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

than run SQL scripts:
```bash
sudo mysql -u root < Database/init.sql
sudo mysql -u root < Database/tables.sql
```

than build the project and run it with:
```bash
#build the project
gradle build -x test

#run the bot
gradle bootRun
```

#### In Windows

Install MySql Server and run `database/init.sql`, `database/tabels.sql` SQL scripts

Than create a file named ```settings.txt``` and paste your tokens into it like in `settings_template.txt`.

After that build and run the bot with:
```bash
gradlew build -x test

gradlew bootRun
``` 

#### Commands

#####Bot usage:
```
prefix + command
```

#####Example: `!cat`

For the list of all commands type `!help`

