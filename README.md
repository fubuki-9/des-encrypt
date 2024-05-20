# des-encrypt
A Client/Server system that encrypts data for transport over a network, and decrypts it at the other end. 
Description
This project is a Java-based Client/Server system . It encrypts data for secure transport over a network using DES (Data Encryption Standard) and decrypts it at the other end. The system uses TCP/IP for client-server communication. While most functionalities work as intended, some issues with decryption remain.

Table of Contents
Installation
Usage
Features
Known Issues
Contributing
License
Installation
To install and run this project locally, follow these steps:

Clone the repository to your local machine:

bash
Copy code
git clone https://github.com/your-username/your-project.git
Navigate to the project directory:

bash
Copy code
cd your-project
Compile the Java files:

bash
Copy code
javac *.java
Start the server:

bash
Copy code
java Server
In a new terminal window, start the client:

bash
Copy code
java Client
Usage
Once the project is installed and running, follow these steps to use it:

Connect to the Server:

Open the client application.
Enter the server's IP address and port number.
Click the "Connect" button to establish a connection with the server.
Load a File:

Click the "Load File" button to select a file from your local machine.
The file's content will be read and prepared for encryption.
Encrypt and Send Data:

The data is automatically encrypted using DES and sent to the server over the network.
Receive and Decrypt Data:

The server receives the encrypted data.
The server decrypts the data and displays the original content.
Features
DES Encryption/Decryption: Encrypt data using DES before sending it over the network and decrypt it upon receiving.
Client-Server Communication: Communicate between client and server using TCP/IP.
Graphical User Interface (GUI): User-friendly GUI for loading files, connecting to the server, and displaying data.
Known Issues
Decryption Errors: Some decrypted data does not match the original data due to issues with the decryption process.
This might be due to incorrect handling of padding or transmission errors. Further debugging is required to resolve this issue.
Contributing
Contributions to this project are welcome! Follow these steps to contribute:

Fork the repository.
Create a new branch for your feature or bug fix:
bash
Copy code
git checkout -b feature-name
Make your changes and commit them:
bash
Copy code
git commit -m "Description of your changes"
Push your changes to your fork:
bash
Copy code
git push origin feature-name
Open a pull request on GitHub.
License
This project is licensed under the MIT License - see the LICENSE file for details.
