# JDodoBot-2.0

New version of JDodoBot. [Add it to your server](https://discordapp.com/oauth2/authorize?client_id=277458741052571648&scope=bot&permissions=2146958591)

## Prerequisites

What things you will need to run the software

```
Java 8, MySql Server, Gradle
```

## Usage

### In Linux
You need to install the following dependencies:
`Java 8`, `MySql Server`, `zip`, `unzip` and `gradle`  

#### Download `install.sh` from the GitHub repo

```bash
# Download install.sh
wget https://raw.githubusercontent.com/ExidCuter/JDodoBot-2.0/master/install.sh

# Make it executable
chmod +x install.sh

# Run it
./install.sh
```

*This file works with APT package manager. If you are using different package manager just change `apt` to the appropriate command.*

#### Or you can manually install them with:

```bash
# Install java
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt install oracle-java8-installer

# Install mysql
sudo apt install mysql-server

# Install zip and unzip
sudo apt install unzip zip

# Install gradle
wget https://services.gradle.org/distributions/gradle-4.10.2-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-4.10.2-bin.zip
export PATH=$PATH:/opt/gradle/gradle-4.10.2/bin
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

### In Windows

Install MySql Server and run `database/init.sql`, `database/tabels.sql` SQL scripts

Than create a file named ```settings.txt``` and paste your tokens into it like in `settings_template.txt`.

After that build and run the bot with:
```bash
gradlew build -x test

gradlew bootRun
``` 

### Commands

#### Bot usage:
```
prefix + command
```

#### Example: `!cat`

For the list of all commands type `!help`

