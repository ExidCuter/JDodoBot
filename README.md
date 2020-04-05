# JDodoBot-2.0

New version of JDodoBot. [Add it to your server](https://discordapp.com/oauth2/authorize?client_id=277458741052571648&scope=bot&permissions=2146958591)

## Prerequisites

What things you will need to run the software

```
Java 8, MySql Server, Gradle
```

## Usage

### Linux
You need to install the following dependencies:
`Java 11`, `MySql Server`, `zip`, `unzip` and `gradle`  

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
wget https://services.gradle.org/distributions/gradle-6.3-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-6.3-bin.zip
export PATH=$PATH:/opt/gradle/gradle-6.3/bin
```

Add environment variables:

```
MYSQL_USER=your_user
MYSQL_PASS=your_pass
BOT_TOKEN=TOKEN
GIPHY_TOKEN=TOKEN
TRN_TOKEN=TOKEN
CLEVER_TOKEN=TOKEN
```
Or change the `application.yml` file
```yaml
dodo-bot:
  token: ${BOT_TOKEN:TOKEN}
  giphy-token: ${GIPHY_TOKEN:TOKEN}
  trn-token: ${TRN_TOKEN:TOKEN}
  clever-token: ${CLEVER_TOKEN:TOKEN}
```

Than run SQL scripts:
```bash
sudo mysql -u root < Database/init.sql
```

and build the project and run it with:
```bash
#build the project
gradle build -x test

#run the bot
gradle bootRun
```

### Commands

#### Bot usage:
```
prefix + command
```

#### Example: `!cat`

For the list of all commands type `!help`

