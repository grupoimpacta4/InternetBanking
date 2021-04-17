#!/bin/bash

echo Config Server: BUILDING
cd config-server
mvn clean install -U
cd ..
clear


echo Login Service: BUILDING
cd login-service
mvn clean install -U
cd ..
clear

echo Login Service: BUILD DONE
echo Transaction Service: BUILDING
cd transaction-service
mvn clean install -U
cd ..
clear

echo Login Service: BUILD DONE
echo Transaction Service: BUILD DONE
echo Oauth Service: BUILDING
cd oauth-service
mvn clean install -U
cd ..
clear

echo Login Service: BUILD DONE
echo Transaction Service: BUILD DONE
echo Oauth Service: BUILD DONE
echo BankAccount Service: BUILDING
cd bankAccount-service
mvn clean install -U
cd ..
clear


echo Login Service: BUILD DONE
echo Transaction Service: BUILD DONE
echo Oauth Service: BUILD DONE
echo BankAccount Service: BUILD DONE

 
echo ---
echo Starting Application...
docker-compose up --build
