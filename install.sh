#!/bin/bash

# colors
RED=$(tput setaf 1)
BLUE=$(tput setaf 4)
CYAN=$(tput setaf 6)
NORMAL=$(tput sgr0)

ver="2.0-ALPHA"
fileLines=()

echo "${CYAN}Deploying JDodoBot ver ${ver} ${NORMAL}"

echo "${CYAN}Installing dependencies... ${NORMAL}"

# install java
echo "${CYAN}Installing OpenJDK 8..."
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update &> /dev/null
sudo apt install openjdk-8-jdk

# mysql
echo "${CYAN}Installing MySQL server..."
sudo apt install mysql-server -y &> /dev/null

# zip and unzip
echo "${CYAN}Installing Zip and UnZip..."
sudo apt install unzip zip -y &> /dev/null

# gradle
echo "${CYAN}Installing Gradle..."
wget https://services.gradle.org/distributions/gradle-4.10.2-bin.zip &> /dev/null
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-4.10.2-bin.zip &> /dev/null
export PATH=$PATH:/opt/gradle/gradle-4.10.2/bin

# JDodoBot-2.0
echo "${CYAN}Cloning JDodoBot from GitHub..."
git clone https://github.com/ExidCuter/JDodoBot-2.0 &> /dev/null

cd JDodoBot-2.0/

echo "${CYAN}Please insert your API tokens (Only Discord Bot Token is required!):${NORMAL}"

while true; do
	read -p "Bot Token: " botToken;
	read -p "Giphy API Key: " giphyToken;
	read -p "FORTNITETRACKER API Key: " fortniteToken;

	echo "${BLUE}Is this ok? ${NORMAL}
	${RED}Bot Token:  ${botToken} ${NORMAL}
	Giphy API Key:  ${giphyToken}
	FORTNITETRACKER API Key:  ${fortniteToken}"
	
	read -p "[Y/N]" answer
	if [ "$answer" == "y" ]
	then
		break
	fi
done

fileLines[0]=$botToken;
fileLines[1]=$giphyToken;
fileLines[2]=$fortniteToken;

touch settings.txt
printf "%s\n" "${fileLines[@]}" > settings.txt

echo "${CYAN}Setting up the database"

sudo mysql -u root < Database/init.sql
sudo mysql -u root < Database/tables.sql

echo "${CYAN}Building the project"
gradle build -x test

echo "${CYAN}Running the Bot!"
gradle bootRun