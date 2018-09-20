#!/bin/bash

# colors
BLACK=$(tput setaf 0)
RED=$(tput setaf 1)
GREEN=$(tput setaf 2)
YELLOW=$(tput setaf 3)
LIME_YELLOW=$(tput setaf 190)
POWDER_BLUE=$(tput setaf 153)
BLUE=$(tput setaf 4)
MAGENTA=$(tput setaf 5)
CYAN=$(tput setaf 6)
WHITE=$(tput setaf 7)
BRIGHT=$(tput bold)
NORMAL=$(tput sgr0)
BLINK=$(tput blink)
REVERSE=$(tput smso)
UNDERLINE=$(tput smul)

ver="2.0-ALPHA"
FILELINES=()

echo "${POWDER_BLUE}Installing dependencies... ${GREEN}"

# install java
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer

# mysql
sudo apt install mysql-server

# zip and unzip
sudo apt install unzip zip

# sdkman
curl -s "https://get.sdkman.io" | bash
source "/home/$USER/.sdkman/bin/sdkman-init.sh"

# gradle
sudo sdk install gradle 4.10.1

echo "${BLUE}Deploying JDodoBot ver ${ver} ${NORMAL}"

# JDodoBot-2.0
git clone https://github.com/ExidCuter/JDodoBot-2.0

cd JDodoBot-2.0/

sudo touch settings.txt

while true; do
	read -p "Bot Token: " bottoken;
	read -p "Giphy API Key: " giphytoken;
	read -p "FORTNITETRACKER API Key: " fortnitetoken;

	echo "${BLUE}Is this ok? ${NORMAL}
	${RED}Bot Token:  ${bottoken} ${NORMAL}
	Giphy API Key:  ${giphytoken}
	FORTNITETRACKER API Key:  ${fortnitetoken}"
	
	read -p "[Y/N]" answer
	if [ "$answer" == "y" ]
	then
		break
	fi
done

FILELINES[0]=$bottoken;
FILELINES[1]=$giphytoken;
FILELINES[2]=$fortnitetoken;

sudo printf "%s\n" "${FILELINES[@]}" > settings.txt

sudo mysql -u root < Database/init.sql
sudo mysql -u root < Database/tables.sql

gradle build -x test 